package com.the_boys.mini_banck.service.impl;

import com.the_boys.mini_banck.dto.CreateAccountRequestDTO;
import com.the_boys.mini_banck.dto.CreateAccountResponseDTO;
import com.the_boys.mini_banck.exception.ResourceNotFoundException;
import com.the_boys.mini_banck.mapper.AccountMapper;
import com.the_boys.mini_banck.model.Account;
import com.the_boys.mini_banck.model.AccountStatus;
import com.the_boys.mini_banck.model.Users;
import com.the_boys.mini_banck.repository.AccountRepository;
import com.the_boys.mini_banck.repository.UsersRepository;
import com.the_boys.mini_banck.service.AccountService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    //inyeccion de dependencias
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UsersRepository usersRepository;


    @Transactional
    @Override
    public CreateAccountResponseDTO createAccount(CreateAccountRequestDTO request) {
        // 1. El usuario debe existir
        Users user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getUserId()));

        // 2. DTO a entidad, y completar lo que el DTO no trae
        Account account = accountMapper.toAccountEntity(request);
        account.setUser(user);
        account.setAccountNumber(generateAccountNumber());

        // 3. Guardar y devolver el response
        Account saved = accountRepository.save(account);
        return accountMapper.toResponseAccountDTO(saved);
    }

    private String generateAccountNumber() {
        String number;
        do {
            number = String.format("%010d", ThreadLocalRandom.current().nextLong(10_000_000_000L));
        } while (accountRepository.existsByAccountNumber(number));
        return number;
    }

    @Override
    @Transactional(readOnly = true)
    public CreateAccountResponseDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));
        return accountMapper.toResponseAccountDTO(account);

    }

    @Override
    public void updateAccount(CreateAccountRequestDTO request) {
        // solo el estado y el balance
    }

    @Override
    @Transactional
    public void deleteAccount(Long id) {
        //actualizar el estado a closed, soft de
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));
        if (account.getStatus() == AccountStatus.CLOSED) {
            throw new BusinessRuleException("La cuenta ya esta cerrada");
        }
        if (account.getBalance().compareTo(BigDecimal.ZERO) != 0) {
            throw new BusinessRuleException("No se puede cerrar una cuenta con saldo");
        }
        account.setStatus(AccountStatus.CLOSED);
        accountRepository.save(account);
    }
}
