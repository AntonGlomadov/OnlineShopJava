package com.glomadovanton.shop.rest.controller;

import com.glomadovanton.shop.orders.OrderService;
import com.glomadovanton.shop.rest.dto.orderRequest.OrderUi;
import com.glomadovanton.shop.rest.dto.orderRequest.Orders;
import com.glomadovanton.shop.rest.dto.test.Greeting;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
