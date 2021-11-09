package com.glomadovanton.shop.orders;

import com.glomadovanton.shop.rest.dto.orderRequest.Order;
import com.glomadovanton.shop.users.UserEntity;

public interface OrderService {

    OrderEntity addOrder(Order order,UserEntity user);
}
