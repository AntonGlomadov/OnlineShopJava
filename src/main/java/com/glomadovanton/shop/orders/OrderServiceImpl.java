package com.glomadovanton.shop.orders;

import com.glomadovanton.shop.rest.dto.orderRequest.Order;
import com.glomadovanton.shop.users.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public OrderEntity addOrder(Order order, UserEntity userEntity) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDelivery(order.getDelivery());
        orderEntity.setDeliveryAddress(order.getDeliveryAddress());
        orderEntity.setDeliveryTime(order.getDeliveryTime());
        orderEntity.setPayment(order.getPayment());
        orderEntity.setStatus(order.getOrderStatus());
        orderEntity.setUser(userEntity);
        return orderRepository.saveAndFlush(orderEntity);
    }
}
