package com.commerce.service;

import com.commerce.client.OrderServiceClient;
import com.commerce.dto.UserDto;
import com.commerce.jpa.UserEntity;
import com.commerce.jpa.UserRepository;
import com.commerce.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
//    private final RestTemplate restTemplate;
    private final OrderServiceClient orderServiceClient;
    private final Environment env;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final UserEntity entity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new User(entity.getEmail(), entity.getEncryptedPwd(), new ArrayList<>());
    }

    public UserDto createUser(final UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));

        final UserEntity userEntity = new UserEntity(userDto);
        final UserEntity savedUserEntity = userRepository.save(userEntity);

        return new UserDto(savedUserEntity);
    }

    @Transactional(readOnly = true)
    public UserDto getUserByUserId(final String userId) {
        final UserEntity userEntity = userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // restTemplate 사용
//        final String orderUrl = String.format(env.getProperty("order_service.url"), userId);
//        final ResponseEntity<List<ResponseOrder>> orderListResponse = restTemplate.exchange(orderUrl, HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<ResponseOrder>>() {
//        });
//        final List<ResponseOrder> orders = orderListResponse.getBody();

        //feignClient 사용
        final List<ResponseOrder> orders = orderServiceClient.getOrders(userId);

        return new UserDto(userEntity, orders);
    }

    @Transactional(readOnly = true)
    public List<UserEntity> getUserByAll() {
        return userRepository.findAll();
    }

    public UserDto getUserDetailsByEmail(final String username) {
        final UserEntity entity = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return new UserDto(entity);
    }
}
