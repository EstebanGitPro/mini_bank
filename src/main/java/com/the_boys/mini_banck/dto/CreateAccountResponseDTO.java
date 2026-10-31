package com.the_boys.mini_banck.dto;

import com.the_boys.mini_banck.model.AccountStatus;
import com.the_boys.mini_banck.model.AccountType;
import com.the_boys.mini_banck.model.CurrencyType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateAccountResponseDTO {

    private String accountNumber;
    private AccountType type;
    private AccountStatus status;
    private BigDecimal balance;
    private CurrencyType currency;
    private Long userId;
}
