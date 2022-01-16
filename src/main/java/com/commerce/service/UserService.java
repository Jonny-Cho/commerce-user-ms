package com.commerce.service;

import com.commerce.dto.UserDto;
import com.commerce.jpa.UserEntity;
import com.commerce.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

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
        final UserEntity userEntity = userRepository.findByUserId(userId);

        return new UserDto(userEntity);
    }

    @Transactional(readOnly = true)
    public List<UserEntity> getUserByAll() {
        return userRepository.findAll();
    }
}
