package com.glomadovanton.shop.orders;

import com.glomadovanton.shop.goods.CakeRepository;
import com.glomadovanton.shop.rest.dto.orderRequest.Order;
import com.glomadovanton.shop.rest.dto.orderRequest.OrderUi;
import com.glomadovanton.shop.rest.dto.orderRequest.Orders;
import com.glomadovanton.shop.rest.dto.orderRequest.Purchase;
import com.glomadovanton.shop.rest.dto.user.User;
import com.glomadovanton.shop.users.UserEntity;
import com.glomadovanton.shop.users.UserRepository;
import com.glomadovanton.shop.users.UserService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final CakeRepository cakeRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, PurchaseRepository purchaseRepository, UserService userService, UserRepository userRepository, CakeRepository cakeRepository) {
        this.orderRepository = orderRepository;
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.cakeRepository = cakeRepository;
    }


    @Override
    public void addOrder(Order order) {

        OrderEntity orderEntity = new OrderEntity();


        orderEntity.setDelivery(order.getDelivery());
        orderEntity.setDeliveryAddress(order.getDeliveryAddress());
        orderEntity.setDeliveryTime(order.getDeliveryTime());
        orderEntity.setPayment(order.getPayment());
        orderEntity.setStatus(order.getOrderStatus());
        orderEntity.setPurchases(order.getPurchases().stream()
                .map(purchase -> {
                    PurchaseEntity purchaseEntity = new PurchaseEntity();
                    purchaseEntity.setNumber(purchase.getNumber());
                    purchaseEntity.setOrder(orderEntity);
                    purchaseEntity.setCake(cakeRepository.findById(purchase.getId()).orElseThrow(RuntimeException::new));
                    return purchaseEntity;
                }).collect(Collectors.toList()));
        orderEntity.setUser(userRepository.findUserEntityByNumber(order.getUser().getNumber()));
        orderRepository.saveAndFlush(orderEntity);
    }

    @Override
    public List<OrderUi> getOrders() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        List<OrderUi> orderList = orderEntities.stream().map(or -> {
            OrderUi order = new OrderUi();
            order.setName(or.getUser().getName());
            order.setNumber(or.getUser().getNumber());
            order.setDelivery(or.getDelivery());
            order.setDeliveryAddress(or.getDeliveryAddress());
            order.setDeliveryTime(or.getDeliveryTime());
            order.setPayment(or.getPayment());
            order.setOrderStatus(or.getStatus());
            /*order.setDeliveryTime(or.getDeliveryTime());

            order.setPayment(order.getPayment());

            order.setOrderStatus(or.getStatus());

            List<PurchaseEntity> purchaseEntityList= or.getPurchases();
            List<Purchase> purchases = purchaseEntityList.stream().map(pu->{
                Purchase purchase = new Purchase();
                purchase.setId(pu.getId());
                purchase.setNumber(pu.getNumber());
                return purchase;
            }).collect(Collectors.toList());

            order.setPurchases(purchases);*/
            return order;
        }).collect(Collectors.toList());
        return orderList;
    }
}
