package com.commerce.dto;

import com.commerce.jpa.UserEntity;
import com.commerce.vo.RequestUser;
import com.commerce.vo.ResponseOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createdAt;
    private String encryptedPwd;
    private List<ResponseOrder> orders;

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

    public UserDto(final UserEntity entity, final List<ResponseOrder> ordersList) {
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.userId = entity.getUserId();
        this.createdAt = new Date();
        this.orders = ordersList;
    }

}
