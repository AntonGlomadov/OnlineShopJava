package com.glomadovanton.shop.orders;

import com.glomadovanton.shop.exception.CakeNotFoundException;
import com.glomadovanton.shop.exception.OrderNotFoundException;
import com.glomadovanton.shop.goods.CakeRepository;
import com.glomadovanton.shop.rest.dto.cake.CakeInOrderInfo;
import com.glomadovanton.shop.rest.dto.orderRequest.Order;
import com.glomadovanton.shop.rest.dto.orderRequest.OrderFullInfo;
import com.glomadovanton.shop.rest.dto.orderRequest.OrdersInfoUI;
import com.glomadovanton.shop.users.UserRepository;
import com.glomadovanton.shop.users.UserService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
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
    public List<OrdersInfoUI> getOrders() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderEntities.stream().map(or -> {
            OrdersInfoUI order = new OrdersInfoUI();
            order.setId(or.getId());
            order.setName(or.getUser().getName());
            order.setNumber(or.getUser().getNumber());
            order.setDelivery(or.getDelivery());
            order.setPayment(or.getPayment());
            order.setOrderStatus(or.getStatus());
            return order;
        }).collect(Collectors.toList());
    }

    @Override
    public OrderFullInfo getOrder(Long id) {
        return orderRepository.findById(id)
                .map(orderEntity -> {
                    OrderFullInfo order = new OrderFullInfo();
                    order.setOrderStatus(orderEntity.getStatus());
                    order.setId(orderEntity.getId());
                    order.setName(orderEntity.getUser().getName());
                    order.setNumber(orderEntity.getUser().getNumber());
                    order.setDelivery(orderEntity.getDelivery());
                    order.setDeliveryAddress(orderEntity.getDeliveryAddress());
                    order.setDeliveryTime(orderEntity.getDeliveryTime());

                    AtomicReference<BigDecimal> paymentSum = new AtomicReference<>(BigDecimal.ZERO);

                    List<CakeInOrderInfo> pairList = orderEntity.getPurchases().stream().map(purchase -> {
                                CakeInOrderInfo cakeInOrderInfo = cakeRepository.findById(purchase.getCake().getId()).map(
                                        cakeEntity -> {
                                            CakeInOrderInfo cake = new CakeInOrderInfo();
                                            cake.setName(cakeEntity.getName());
                                            cake.setPrice(cakeEntity.getPrice());
                                            return cake;
                                        }
                                ).orElse(new CakeInOrderInfo());
                                cakeInOrderInfo.setNumber(purchase.getNumber());
                                paymentSum.updateAndGet(v -> v.add(cakeInOrderInfo.getPrice().multiply(BigDecimal.valueOf(purchase.getNumber()))));
                                return cakeInOrderInfo;
                            }
                    ).collect(Collectors.toList());


                    order.setCakesList(pairList);
                    order.setPaymentSum(paymentSum.get());
                    order.setPayment(orderEntity.getPayment());
                    return order;
                })
                .orElseThrow(() -> new OrderNotFoundException("No such order"));

    }

    @Override
    public void changeOrderStatus(Long id, OrderStatus status) {
        orderRepository.updateStatus(id,status);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
