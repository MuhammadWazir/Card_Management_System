package com.example.cms.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String status; // ACTIVE, INACTIVE

    @Column(nullable = false)
    private BigDecimal balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Card> cards;

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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}

