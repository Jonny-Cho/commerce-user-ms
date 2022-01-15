package com.commerce.dto;

import com.commerce.jpa.UserEntity;
import com.commerce.vo.RequestUser;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createdAt;
    private String encryptedPwd;

    public UserDto(final RequestUser requestUser) {
        this.email = requestUser.getEmail();
        this.name = requestUser.getName();
        this.pwd = requestUser.getPwd();
        this.createdAt = new Date();
    }

    public UserDto(final UserEntity entity) {
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.userId = entity.getUserId();
        this.createdAt = new Date();
    }

    public static UserDto from(final UserEntity userEntity) {
        return null;
    }
}
