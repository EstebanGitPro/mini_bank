package com.the_boys.mini_banck.dto;

import com.the_boys.mini_banck.model.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequestDTO {
    @NotNull
    private Long userId;
    @NotNull
    private AccountType type;



}
