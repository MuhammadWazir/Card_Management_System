package com.example.cms.service;

import com.example.cms.dto.TransactionRequest;
import com.example.cms.exception.ValidationException;
import com.example.cms.model.Account;
import com.example.cms.model.Card;
import com.example.cms.model.Transaction;
import com.example.cms.repository.AccountRepository;
import com.example.cms.repository.CardRepository;
import com.example.cms.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              CardRepository cardRepository,
                              AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
    }

    public Transaction create(TransactionRequest request) {
        Card card = cardRepository.findById(request.getCardId())
                .orElseThrow(() -> new ValidationException("Invalid card"));

        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new ValidationException("Invalid account"));

        if (!"ACTIVE".equals(card.getStatus()) || card.getExpiry().before(new java.util.Date())) {
            throw new ValidationException("Card not eligible");
        }

        if (!"ACTIVE".equals(account.getStatus())) {
            throw new ValidationException("Account not eligible");
        }

        if ("D".equals(request.getTransactionType()) &&
                account.getBalance().compareTo(request.getTransactionAmount()) < 0) {
            throw new ValidationException("Insufficient balance");
        }

        BigDecimal newBalance = "C".equals(request.getTransactionType())
                ? account.getBalance().add(request.getTransactionAmount())
                : account.getBalance().subtract(request.getTransactionAmount());

        account.setBalance(newBalance);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(request.getTransactionAmount());
        transaction.setTransactionDate(Timestamp.from(Instant.now()));
        transaction.setTransactionType(request.getTransactionType());
        transaction.setAccount(account);
        transaction.setCard(card);

        return transactionRepository.save(transaction);
    }
}
