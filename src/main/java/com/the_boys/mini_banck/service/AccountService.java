package com.the_boys.mini_banck.service;

import com.the_boys.mini_banck.dto.CreateAccountRequestDTO;
import com.the_boys.mini_banck.dto.CreateAccountResponseDTO;

public interface AccountService {
    CreateAccountResponseDTO createAccount(CreateAccountRequestDTO createAccountRequestDTO);
    CreateAccountResponseDTO getAccountById(Long id);
    void updateAccount(CreateAccountRequestDTO createAccountRequestDTO);
    void deleteAccount(Long id);
}
