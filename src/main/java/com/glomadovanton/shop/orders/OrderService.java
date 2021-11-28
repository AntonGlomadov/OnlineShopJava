package com.glomadovanton.shop.orders;

import com.glomadovanton.shop.rest.dto.orderRequest.Order;
import com.glomadovanton.shop.rest.dto.orderRequest.OrderUi;
import com.glomadovanton.shop.rest.dto.orderRequest.Orders;
import com.glomadovanton.shop.users.UserEntity;

import java.util.List;

public interface OrderService {

     void addOrder(Order order);

     List<OrderUi> getOrders();
}
