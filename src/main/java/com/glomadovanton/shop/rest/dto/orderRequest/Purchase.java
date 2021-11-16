package com.glomadovanton.shop.rest.dto.orderRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class Purchase {
    @NotNull
    private Long id;

    @NotNull
    private Integer number;

}
