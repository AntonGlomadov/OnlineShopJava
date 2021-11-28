package com.glomadovanton.shop.rest.controller;

import com.glomadovanton.shop.exception.UserExistException;
import com.glomadovanton.shop.goods.CakesService;
import com.glomadovanton.shop.orders.OrderService;
import com.glomadovanton.shop.orders.PurchaseService;
import com.glomadovanton.shop.rest.dto.cake.Cake;
import com.glomadovanton.shop.rest.dto.cake.CakeFullInf;
import com.glomadovanton.shop.rest.dto.cake.Cakes;
import com.glomadovanton.shop.rest.dto.orderRequest.Order;
import com.glomadovanton.shop.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


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

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(path = "addOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody @Valid Order newOrder) {
        try {
            userService.addUser(newOrder.getUser());
        }
        catch (UserExistException ignored){
        }
        orderService.addOrder(newOrder);
    }
}
