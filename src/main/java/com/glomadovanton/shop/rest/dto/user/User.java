package com.glomadovanton.shop.rest.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Schema(description = "Info about purchase")
@Validated
public class User {

    @NotNull
    @Schema(description = "number", required = true)
    @JsonProperty("number")
    private String number;

    @NotNull
    @Schema(description = "name", required = true)
    @JsonProperty("name")
    private String name;

}
