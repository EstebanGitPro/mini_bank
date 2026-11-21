package com.the_boys.mini_banck.service.impl;

import com.the_boys.mini_banck.dto.CreateAccountRequestDTO;
import com.the_boys.mini_banck.dto.CreateAccountResponseDTO;
import com.the_boys.mini_banck.exception.BusinessRuleException;
import com.the_boys.mini_banck.mapper.AccountMapper;
import com.the_boys.mini_banck.model.Account;
import com.the_boys.mini_banck.model.Users;
import com.the_boys.mini_banck.repository.AccountRepository;
import com.the_boys.mini_banck.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLOutput;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    private static final String ELEVEN_DIGITS = "\\d{11}";

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private AccountServiceImpl service;

    private CreateAccountRequestDTO request;

    @BeforeEach
    void setUp() {
        request = new CreateAccountRequestDTO();
        request.setUserId(1L);

        when(usersRepository.findById(1L)).thenReturn(Optional.of(new Users()));
        // Must be a real Account so setAccountNumber actually stores the value
        when(accountMapper.toAccountEntity(request)).thenReturn(new Account());
    }

    @Test
    void createAccount_whenNumberIsUnique_assignsElevenDigitNumber() {
        when(accountRepository.existsByAccountNumber(anyString())).thenReturn(false);
        when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));
        System.out.println("=== Antes de crear la cuenta ===");

        CreateAccountResponseDTO response=service.createAccount(request);

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).save(captor.capture());

        Account guardada=captor.getValue();
        System.out.println("Numero generado " + guardada.getAccountNumber());
        System.out.println("Longitud: " + guardada.getAccountNumber().length());
        System.out.println("Cuenta completa: " + guardada);
        System.out.println("Response del service: " + response);

        assertThat(captor.getValue().getAccountNumber()).matches(ELEVEN_DIGITS);
        verify(accountRepository, times(1)).existsByAccountNumber(anyString());
    }

    @Test
    void createAccount_whenFirstNumberCollides_retriesAndSaves() {
        when(accountRepository.existsByAccountNumber(anyString())).thenReturn(true, false);
        when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

        service.createAccount(request);

        ArgumentCaptor<String> checkedNumbers = ArgumentCaptor.forClass(String.class);
        verify(accountRepository, times(2)).existsByAccountNumber(checkedNumbers.capture());

        ArgumentCaptor<Account> saved = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).save(saved.capture());

        String assigned = saved.getValue().getAccountNumber();
        assertThat(assigned).matches(ELEVEN_DIGITS);
        // The saved number is the second candidate, the one that did not collide
        assertThat(assigned).isEqualTo(checkedNumbers.getAllValues().get(1));
    }

    @Test
    void createAccount_whenAllAttemptsCollide_throwsBusinessRuleException() {
        when(accountRepository.existsByAccountNumber(anyString())).thenReturn(true);

        assertThatThrownBy(() -> service.createAccount(request))
                .isInstanceOf(BusinessRuleException.class)
                .hasMessage("Could not generate a unique account number");

        verify(accountRepository, times(5)).existsByAccountNumber(anyString());
        verify(accountRepository, never()).save(any());
    }
}
