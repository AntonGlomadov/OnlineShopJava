package com.glomadovanton.shop.rest.controller;

import com.glomadovanton.shop.goods.CakesService;
import com.glomadovanton.shop.orders.OrderEntity;
import com.glomadovanton.shop.orders.OrderService;
import com.glomadovanton.shop.orders.PurchaseEntity;
import com.glomadovanton.shop.orders.PurchaseService;
import com.glomadovanton.shop.rest.dto.cake.Cake;
import com.glomadovanton.shop.rest.dto.cake.CakeFullInf;
import com.glomadovanton.shop.rest.dto.cake.Cakes;
import com.glomadovanton.shop.rest.dto.orderRequest.OrderFullInf;
import com.glomadovanton.shop.rest.dto.orderRequest.Purchase;
import com.glomadovanton.shop.users.UserEntity;
import com.glomadovanton.shop.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Validated
public class UserController {
    private final UserService userService;
    private final CakesService cakesService;
    private final PurchaseService purchaseService;
    private final OrderService orderService;


    @Autowired
    public UserController(UserService userService, CakesService cakesService, PurchaseService purchaseService, OrderService orderService) {
        this.cakesService = cakesService;
        this.userService = userService;
        this.purchaseService = purchaseService;
        this.orderService = orderService;
    }

    @GetMapping(value = "cakes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cakes cakes() {
        return cakesService.getCakes();
    }

    @GetMapping(value = "cake/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CakeFullInf getCakeById(@PathVariable Long id) {
        return cakesService.getCake(id);
    }

    @PostMapping(path = "addCake", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cake> createCake(@RequestBody @Valid CakeFullInf newCake) {
        cakesService.addCake(newCake);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "addOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderFullInf> createOrder(@RequestBody @Valid OrderFullInf newOrder) {
        UserEntity userEntity = userService.addUser(newOrder.getUser());
        OrderEntity orderEntity = orderService.addOrder(newOrder.getOrder(),userEntity);
        for (Purchase purchase :newOrder.getPurchases()){
            purchaseService.addPurchase(orderEntity,cakesService.getCakeEntity(purchase.getCakeId()),purchase.getNumber());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
