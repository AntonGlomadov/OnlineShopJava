package com.glomadovanton.shop.rest.controller;

import com.glomadovanton.shop.goods.CakesService;
import com.glomadovanton.shop.goods.State;
import com.glomadovanton.shop.orders.OrderService;
import com.glomadovanton.shop.rest.dto.cake.CakeFullInf;
import com.glomadovanton.shop.rest.dto.orderRequest.ChangeInOrder;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "/start")
    public ModelAndView getStart(){
        ModelAndView maw = new ModelAndView("start");
        return maw;
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

    @GetMapping(value = "/cake/edit")
    public ModelAndView getCakeForm(){
        ModelAndView maw = new ModelAndView("cakeEditForm");
        maw.addObject("cake", new CakeFullInf());
        return maw;
    }

    @PostMapping(value = "/cake/edit")
    public RedirectView addCake(CakeFullInf cake){
        cake.setState(State.AVAILABLE);
        Long id = cakesService.addCake(cake);
        return new RedirectView("/admin/cake/"+id.toString());
    }

    @GetMapping(value = "cake/{id}")
    public ModelAndView getCakeById(@PathVariable Long id) {
        ModelAndView maw = new ModelAndView("cake");
        maw.addObject("cake",cakesService.getCake(id));
        return maw;
    }

    @GetMapping(value = "/cake/delete/{id}")
    public RedirectView deleteCake(@PathVariable Long id){
        cakesService.deleteCake(id);
        return new RedirectView("/admin/cakes");
    }

}
