package com.the_boys.mini_banck.repository;

import com.the_boys.mini_banck.model.TransactionStatus;
import com.the_boys.mini_banck.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    Optional<Transactions> findByReferenceCode(String referenceCode);

    boolean existsByReferenceCode(String referenceCode);

    List<Transactions> findByAccountIdOrderByCreatedAtDesc(Long accountId);

    List<Transactions> findByTransactionStatus(TransactionStatus transactionStatus);
}
