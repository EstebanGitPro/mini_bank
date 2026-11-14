package com.the_boys.mini_banck.repository;

import com.the_boys.mini_banck.model.TransactionStatus;
import com.the_boys.mini_banck.model.Transactions;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    Optional<Transactions> findByReferenceCode(String referenceCode);

    boolean existsByReferenceCode(String referenceCode);

    @EntityGraph(attributePaths = {"account", "destinationAccount", "destinationAccount.user"})
    List<Transactions> findByAccount_IdOrDestinationAccount_idOrderByCreatedAtDesc(Long accountId, Long destinationAccountId);

    List<Transactions> findByTransactionStatus(TransactionStatus transactionStatus);
}
