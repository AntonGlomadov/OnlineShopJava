package com.glomadovanton.shop.rest.dto.orderRequest;

import com.glomadovanton.shop.orders.Delivery;
import com.glomadovanton.shop.orders.OrderStatus;
import com.glomadovanton.shop.orders.Payment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Schema
public class OrdersInfoUI {
    private Long id;
    private String name;
    private String number;
    private Delivery delivery;
    private Payment payment;
    private OrderStatus orderStatus;
}
