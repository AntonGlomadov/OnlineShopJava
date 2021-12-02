package com.glomadovanton.shop.rest.dto.orderRequest;

import com.glomadovanton.shop.orders.OrderStatus;
import lombok.Data;

@Data
public class ChangeInOrder {
    private OrderStatus status;
}
