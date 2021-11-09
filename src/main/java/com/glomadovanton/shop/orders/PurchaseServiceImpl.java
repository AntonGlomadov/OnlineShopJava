package com.glomadovanton.shop.orders;

import com.glomadovanton.shop.goods.CakeEntity;
import com.glomadovanton.shop.rest.dto.orderRequest.Order;
import com.glomadovanton.shop.rest.dto.orderRequest.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService{
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }


    @Override
    public void addPurchase(OrderEntity orderEntity, CakeEntity cakeEntity, Integer number) {
        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setNumber(number);
        purchase.setOrder(orderEntity);
        purchase.setCake(cakeEntity);
        purchaseRepository.saveAndFlush(purchase);
    }
}
