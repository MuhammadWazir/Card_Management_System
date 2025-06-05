package com.example.cms.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Card {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String status; // ACTIVE, INACTIVE

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date expiry;

    @Column(nullable = false, unique = true)
    private String cardNumber; // Should be encrypted in real implementation

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}

