package com.commerce.vo;

import com.commerce.dto.UserDto;
import lombok.Getter;

@Getter
public class ResponseUser {
    private String email;
    private String name;
    private String userId;

    public ResponseUser(final UserDto user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.userId = user.getUserId();
    }
}
