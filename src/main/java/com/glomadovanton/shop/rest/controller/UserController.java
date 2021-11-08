package com.glomadovanton.shop.rest.controller;

import com.glomadovanton.shop.goods.CakesService;
import com.glomadovanton.shop.rest.dto.cake.Cake;
import com.glomadovanton.shop.rest.dto.cake.CakeFullInf;
import com.glomadovanton.shop.rest.dto.cake.Cakes;
import com.glomadovanton.shop.rest.dto.orderRequest.OrderFullInf;
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

    @Autowired
    public UserController( UserService userService, CakesService cakesService) {
        this.cakesService = cakesService;
        this.userService = userService;
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
        userService.addUser(newOrder.getUser());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
