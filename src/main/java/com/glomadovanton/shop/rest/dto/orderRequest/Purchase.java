package com.glomadovanton.shop.rest.dto.orderRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Schema(description = "Info about order")
@Validated
public class Purchase {
    @Null
    @Schema(description = "id", required = false)
    @JsonProperty("id")
    private Long id;

    @NotNull
    @Schema(description = "cakeId", required = true)
    @JsonProperty("cakeId")
    private Long cakeId;

    @NotNull
    @Schema(description = "number", required = true)
    @JsonProperty("number")
    private Integer number;
}
