package com.glomadovanton.shop.rest.dto.cake;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CakeInOrderInfo {
    private String name;
    private BigDecimal price;
}
