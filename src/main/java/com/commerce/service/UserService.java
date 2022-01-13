package com.commerce.service;

import com.commerce.dto.UserDto;
import com.commerce.jpa.UserEntity;
import com.commerce.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserDto createUser(final UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));

        final UserEntity userEntity = new UserEntity(userDto);
        final UserEntity savedUserEntity = userRepository.save(userEntity);

        return new UserDto(savedUserEntity);
    }

}
