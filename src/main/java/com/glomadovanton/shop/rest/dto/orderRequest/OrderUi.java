package com.glomadovanton.shop.rest.dto.orderRequest;

import com.glomadovanton.shop.orders.Delivery;
import com.glomadovanton.shop.orders.OrderStatus;
import com.glomadovanton.shop.orders.Payment;

import java.time.LocalDateTime;

public class OrderUi {

    private String name;
    private String number;
    private Delivery delivery;
    private String deliveryAddress;
    private LocalDateTime deliveryTime;
    private Payment payment;
    private OrderStatus orderStatus;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }




}
