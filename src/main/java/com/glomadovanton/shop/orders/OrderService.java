package com.glomadovanton.shop.orders;

import com.glomadovanton.shop.rest.dto.orderRequest.Order;
import com.glomadovanton.shop.rest.dto.orderRequest.OrderFullInfo;
import com.glomadovanton.shop.rest.dto.orderRequest.OrdersInfoUI;

import java.util.List;

public interface OrderService {

     void addOrder(Order order);

     List<OrdersInfoUI> getOrders();

     OrderFullInfo getOrder(Long id);

     void changeOrderStatus(Long id, OrderStatus status);

     void deleteOrder(Long id);
}
