package com.glomadovanton.shop.rest.dto.orderRequest;

import com.glomadovanton.shop.orders.Delivery;
import com.glomadovanton.shop.orders.OrderStatus;
import com.glomadovanton.shop.orders.Payment;
import com.glomadovanton.shop.rest.dto.cake.CakeInOrderInfo;
import javafx.util.Pair;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class OrderFullInfo {

    private String name;
    private String number;
    private Delivery delivery;
    private String deliveryAddress;
    private LocalDateTime deliveryTime;
    private Payment payment;
    private OrderStatus orderStatus;
    private List<Pair<CakeInOrderInfo,Integer>> cakesList;
    private BigDecimal paymentSum;
}
