package com.glomadovanton.shop.rest.dto.orderRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.glomadovanton.shop.orders.Delivery;
import com.glomadovanton.shop.orders.OrderStatus;
import com.glomadovanton.shop.orders.Payment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
@Schema(description = "Info about purchase")
@Validated
public class Purchase   {
    @Null
    @Schema(description = "id", required = false)
    @JsonProperty("id")
    private Long id;

    @NotNull
    @Schema(description = "cakeId", required = true)
    @JsonProperty("cakeId")
    private Long cakeId;

    @NotNull
    @Schema(description = "orderId", required = true)
    @JsonProperty("orderId")
    private Long orderId;

    @NotNull
    @Schema(description = "delivery need", required = true)
    @JsonProperty("delivery")
    private Delivery delivery;

    @NotNull
    @Schema(description = "order status", required = true)
    @JsonProperty("orderStatus")
    private OrderStatus orderStatus;

    @NotNull
    @Schema(description = "payment status", required = true)
    @JsonProperty("payment")
    private Payment payment;

    @NotNull
    @Schema(description = "delivery address", required = true)
    @JsonProperty("deliveryAddress")
    private String deliveryAddress;

    @NotNull
    @Schema(description = "delivery time", required = true)
    @JsonProperty("delivery time")
    private LocalDateTime deliveryTime;

}
