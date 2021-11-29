package com.glomadovanton.shop.rest.controller;

import com.glomadovanton.shop.orders.Delivery;
import com.glomadovanton.shop.orders.OrderService;
import com.glomadovanton.shop.rest.dto.cake.CakeFullInf;
import com.glomadovanton.shop.rest.dto.orderRequest.OrderFullInfo;
import com.glomadovanton.shop.rest.dto.test.Greeting;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")

public class AdminController {

    private final OrderService orderService;

    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/orders")
    public ModelAndView getOrders(){
        ModelAndView maw = new ModelAndView("orders");
        maw.addObject("orders", orderService.getOrders());
        return maw;
    }

    @GetMapping(value = "/order/{id}")
    public ModelAndView getOrder(@PathVariable Long id){
        ModelAndView maw = new ModelAndView("order");
        maw.addObject("order", orderService.getOrder(id));
        return maw;
    }

}
