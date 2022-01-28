package com.commerce.client;

import com.commerce.vo.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

    @GetMapping("/{userId}/orders")
    List<ResponseOrder> getOrders(@PathVariable final String userId);

}
