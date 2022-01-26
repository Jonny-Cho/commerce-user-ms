package com.commerce.vo;

import com.commerce.dto.UserDto;
import com.commerce.jpa.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    private String email;
    private String name;
    private String userId;

    private List<ResponseOrder> orders;

    public ResponseUser(final UserDto userDto) {
        this.email = userDto.getEmail();
        this.name = userDto.getName();
        this.userId = userDto.getUserId();
        this.orders = userDto.getOrders();
    }

    public ResponseUser(final UserEntity entity) {
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.userId = entity.getUserId();
    }

    public static List<ResponseUser> from(final List<UserEntity> entity) {
        return entity.stream()
            .map(ResponseUser::new)
            .collect(Collectors.toList());
    }

}
