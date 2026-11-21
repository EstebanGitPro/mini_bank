package com.the_boys.mini_banck.service.impl;

import com.the_boys.mini_banck.dto.TransactionResponseDTO;
import com.the_boys.mini_banck.dto.TransferRequestDTO;
import com.the_boys.mini_banck.exception.BusinessRuleException;
import com.the_boys.mini_banck.exception.ResourceNotFoundException;
import com.the_boys.mini_banck.mapper.TransactionMapper;
import com.the_boys.mini_banck.model.*;
import com.the_boys.mini_banck.repository.AccountRepository;
import com.the_boys.mini_banck.repository.TransactionsRepository;
import com.the_boys.mini_banck.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionServiceImp implements TransactionService {


    private final TransactionsRepository transactionsRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImp(
            TransactionsRepository transactionsRepository,
            AccountRepository accountRepository,
            TransactionMapper transactionMapper){
        this.transactionsRepository = transactionsRepository;
        this.accountRepository = accountRepository;
        this.transactionMapper = transactionMapper;

    }

    private Account findAccount(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber", accountNumber));
    }

    private void validateActive(Account account){
        if (account.getStatus() != AccountStatus.ACTIVE){
            throw new BusinessRuleException("Account " + account.getAccountNumber() + "is not Active");
        }
    }

    private String generateReferenceCode(){
        String code;
        do{
            code = "TRX-" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
        }while(transactionsRepository.existsByReferenceCode(code));
        return code;
    }

    @Transactional
    @Override
    public TransactionResponseDTO transfer(TransferRequestDTO request){
        if(request.getAccountNumber().equals(request.getDestinationAccountNumber())){
            throw new BusinessRuleException("Source and destination account must be different");
        }

        Account source = findAccount(request.getAccountNumber());
        Account destination = findAccount(request.getDestinationAccountNumber());

        validateActive(source);
        validateActive(destination);

        if(source.getCurrency() != destination.getCurrency()){
            throw new BusinessRuleException("Accounts must have the same currency");
        }

        if(source.getBalance().compareTo(request.getAmount()) < 0 ){
            throw new BusinessRuleException("Insufficient funds");
        }

        source.setBalance(source.getBalance().subtract(request.getAmount()));
        destination.setBalance(destination.getBalance().add(request.getAmount()));

        Transactions transaction = Transactions.builder()
                .account(source)
                .destinationAccount(destination)
                .transactionType(TransactionType.TRANSFER)
                .transactionStatus(TransactionStatus.SUCCESS)
                .amount(request.getAmount())
                .description(request.getDescription())
                .referenceCode(generateReferenceCode())
                .build();

        Transactions saved = transactionsRepository.save(transaction);
        return  transactionMapper.toResponseDTO(saved);
    }



}
