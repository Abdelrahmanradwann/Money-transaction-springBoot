package com.example.FirstProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import com.example.FirstProject.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findBySenderAccountNumberOrRecipientAccountNumber(String  senderAccountNumber, String  recipientAccountNumber, Pageable pageable);

    Page<Transaction> findBySenderAccountNumberOrRecipientAccountNumberAndTransactionTimeBetween(
            String  senderAccountId, String recipientAccountId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);


}
