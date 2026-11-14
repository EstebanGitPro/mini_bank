package com.the_boys.mini_banck.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.the_boys.mini_banck.model.CurrencyType;
import com.the_boys.mini_banck.model.TransactionStatus;
import com.the_boys.mini_banck.model.TransactionType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponseDTO {

    private String referenceCode;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private BigDecimal amount;
    private CurrencyType currencyType;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private String destinationAccountHolder;
    private String description;
    private String failureReason;
    private LocalDateTime createdAt;

}
