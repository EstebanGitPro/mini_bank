package com.the_boys.mini_banck.mapper;

import com.the_boys.mini_banck.dto.CreateAccountRequestDTO;
import com.the_boys.mini_banck.dto.CreateAccountResponseDTO;
import com.the_boys.mini_banck.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accountNumber", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target="balance", ignore = true)
    @Mapping(target="status", ignore = true)
    @Mapping(target = "currency",ignore = true)

    Account toAccountEntity(CreateAccountRequestDTO createAccountRequestDTO);


    @Mapping(target = "userId", source = "user.id")
    CreateAccountResponseDTO toResponseAccountDTO(Account account);
}
