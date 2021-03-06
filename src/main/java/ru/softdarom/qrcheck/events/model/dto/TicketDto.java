package ru.softdarom.qrcheck.events.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Generated;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDto {

    @JsonProperty("ticketId")
    private Long id;

    @NotNull
    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("availableQuantity")
    private Integer availableQuantity;

    @NotNull
    @JsonProperty("cost")
    private BigDecimal cost;

    @JsonProperty("price")
    private BigDecimal price;

}