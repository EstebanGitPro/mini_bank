package com.the_boys.mini_banck.controller;

import com.the_boys.mini_banck.dto.CreateAccountRequestDTO;
import com.the_boys.mini_banck.dto.CreateAccountResponseDTO;
import com.the_boys.mini_banck.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<CreateAccountResponseDTO> create(
            @Valid @RequestBody CreateAccountRequestDTO request) {
        CreateAccountResponseDTO response = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
