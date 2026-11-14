package com.the_boys.mini_banck.mapper;

import com.the_boys.mini_banck.dto.TransactionResponseDTO;
import com.the_boys.mini_banck.model.Transactions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {

    @Mapping(target = "sourceAccountNumber", source = "account.accountNumber")
    @Mapping(target = "currencyType", source = "account.currency")
    @Mapping(target = "destinationAccountNumber", source = "destinationAccount.accountNumber")
    @Mapping(target = "destinationAccountHolder", source = "destinationAccount.user.name")
    TransactionResponseDTO toResponseDTO(Transactions transaction);

    List<TransactionResponseDTO> toResponseDTOList(List<Transactions> transactions);
}
