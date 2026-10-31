package com.the_boys.mini_banck.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequestDTO {

    @NotNull(message = "Source account id is required")
    private Long accountId;

    @NotNull(message = "Destination account id is required")
    private Long destinationAccountId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    @Digits(integer = 17, fraction = 2, message = "Amount must have at most 2 decimals")
    private BigDecimal amount;

    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;
}
