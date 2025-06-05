package com.example.cms.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class TransactionRequest {
    private BigDecimal transactionAmount;
    private String transactionType; // C or D
    private UUID accountId;
    private UUID cardId;

    // Getters and Setters

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public UUID getCardId() {
        return cardId;
    }

    public void setCardId(UUID cardId) {
        this.cardId = cardId;
    }
}
