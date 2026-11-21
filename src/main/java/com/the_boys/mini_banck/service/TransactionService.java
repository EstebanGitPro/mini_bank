package com.the_boys.mini_banck.service;

import com.the_boys.mini_banck.dto.TransactionRequestDTO;
import com.the_boys.mini_banck.dto.TransactionResponseDTO;
import com.the_boys.mini_banck.dto.TransferRequestDTO;

import java.util.List;

public interface TransactionService {

   // TransactionResponseDTO deposit(TransactionRequestDTO request);
   // TransactionResponseDTO withdraw(TransactionRequestDTO request);
    TransactionResponseDTO transfer(TransferRequestDTO request);
    //List<TransactionResponseDTO> getHistory(String accountNumber);
}

