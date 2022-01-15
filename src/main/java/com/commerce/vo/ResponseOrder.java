package com.commerce.vo;

import lombok.Getter;

import java.util.Date;

@Getter
public class ResponseOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private Date createdAt;
    private String orderId;
}
