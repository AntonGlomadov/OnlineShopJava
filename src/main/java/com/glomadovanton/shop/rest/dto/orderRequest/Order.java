package com.glomadovanton.shop.rest.dto.orderRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.glomadovanton.shop.orders.Delivery;
import com.glomadovanton.shop.orders.OrderStatus;
import com.glomadovanton.shop.orders.Payment;
import com.glomadovanton.shop.rest.dto.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "Info about order")
@Validated
public class Order {

    @NotNull
    @Schema(description = "user info ", required = true)
    @JsonProperty("user")
    private User user;

    @NotNull
    @Schema(description = "delivery need", required = true)
    @JsonProperty("delivery")
    private Delivery delivery;

    @NotNull
    @Schema(description = "delivery address", required = true)
    @JsonProperty("deliveryAddress")
    private String deliveryAddress;

    @NotNull
    @Schema(description = "delivery time", required = true)
    @JsonProperty("delivery time")
    private LocalDateTime deliveryTime;

    @NotNull
    @Schema(description = "payment status", required = true)
    @JsonProperty("payment")
    private Payment payment;

    @NotNull
    @Schema(description = "order status", required = true)
    @JsonProperty("orderStatus")
    private OrderStatus orderStatus;

    @NotNull
    @Schema(description = "purchases", required = true)
    @JsonProperty("purchases")
    private List<Purchase> purchases;
}
