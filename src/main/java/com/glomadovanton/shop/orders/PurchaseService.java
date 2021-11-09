package com.glomadovanton.shop.orders;

import com.glomadovanton.shop.goods.CakeEntity;
import com.glomadovanton.shop.rest.dto.orderRequest.Order;
import com.glomadovanton.shop.rest.dto.orderRequest.Purchase;

import java.util.List;

public interface PurchaseService {

    void addPurchase(OrderEntity orderEntity, CakeEntity cakeEntity,Integer number);

}
