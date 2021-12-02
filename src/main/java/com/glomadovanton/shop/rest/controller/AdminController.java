package com.glomadovanton.shop.rest.controller;

import com.glomadovanton.shop.goods.CakesService;
import com.glomadovanton.shop.orders.OrderService;
import com.glomadovanton.shop.rest.dto.cake.CakeFullInf;
import com.glomadovanton.shop.rest.dto.orderRequest.ChangeInOrder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/admin")

public class AdminController {

    private final OrderService orderService;
    private final CakesService cakesService;

    public AdminController(OrderService orderService, CakesService cakesService) {
        this.orderService = orderService;
        this.cakesService = cakesService;
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
        maw.addObject("status", new ChangeInOrder() );
        maw.addObject("order", orderService.getOrder(id));
        return maw;
    }

    @PostMapping(value = "/order/change/{id}")
    public RedirectView changeOrder(@PathVariable Long id, ChangeInOrder changeInOrder){
        orderService.changeOrderStatus(id,changeInOrder.getStatus());
        return new RedirectView("/admin/order/{id}");
    }

    @GetMapping(value = "/order/delete/{id}")
    public RedirectView deletOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return new RedirectView("/admin/orders");
    }

    @GetMapping(value = "/cakes")
    public ModelAndView getCakes(){
        ModelAndView maw = new ModelAndView("cakes");
        maw.addObject("cakes", cakesService.getCakes().getCakeList());
        return maw;
    }

    @GetMapping(value = "/order/edit")
    public ModelAndView addCake(@PathVariable Long id){
        ModelAndView maw = new ModelAndView("cakeEditForm");
        maw.addObject("cake", new CakeFullInf());
        return maw;
    }

    @GetMapping(value = "/cake/delete/{id}")
    public RedirectView deleteCake(@PathVariable Long id){
        return new RedirectView("/admin/orders");
    }

}
