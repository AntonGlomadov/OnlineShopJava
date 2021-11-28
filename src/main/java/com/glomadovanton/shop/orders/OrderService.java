package com.glomadovanton.shop.orders;

import com.glomadovanton.shop.rest.dto.orderRequest.Order;
import com.glomadovanton.shop.rest.dto.orderRequest.Orders;
import com.glomadovanton.shop.users.UserEntity;

public interface OrderService {

     void addOrder(Order order);

     Orders getOrders();
}
