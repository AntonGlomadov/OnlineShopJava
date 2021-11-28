package com.glomadovanton.shop.rest.controller;

import com.glomadovanton.shop.orders.OrderService;
import com.glomadovanton.shop.rest.dto.orderRequest.Orders;
import com.glomadovanton.shop.rest.dto.test.Greeting;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final OrderService orderService;

    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/orders")
    public String getOrders(Model model){
        Orders orders = orderService.getOrders();
        model.addAttribute("greeting", orders);
        return "orders";
    }
}
