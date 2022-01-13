package com.commerce.service;

import com.commerce.dto.UserDto;
import com.commerce.jpa.UserEntity;
import com.commerce.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto createUser(final UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        final UserEntity userEntity = new UserEntity(userDto);
        userRepository.save(userEntity);

        return null;
    }

}
