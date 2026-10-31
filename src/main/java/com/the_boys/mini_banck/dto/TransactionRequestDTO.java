package com.the_boys.mini_banck.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

// Used for DEPOSIT and WITHDRAWAL; the type comes from the endpoint
@Data
public class TransactionRequestDTO {

    @NotNull(message = "Account id is required")
    private Long accountId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    @Digits(integer = 17, fraction = 2, message = "Amount must have at most 2 decimals")
    private BigDecimal amount;

    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;
}
