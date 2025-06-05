package com.example.cms.service;

import com.example.cms.dto.CardRequest;
import com.example.cms.model.Account;
import com.example.cms.model.Card;
import com.example.cms.repository.AccountRepository;
import com.example.cms.repository.CardRepository;
import com.example.cms.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    public CardService(CardRepository cardRepository, AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
    }

    public Card create(CardRequest request) {
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new NotFoundException("Account not found"));

        Card card = new Card();
        card.setStatus(request.getStatus());
        card.setExpiry(request.getExpiry());
        card.setCardNumber(request.getCardNumber());
        card.setAccount(account);

        return cardRepository.save(card);
    }

    public Card get(UUID id) {
        return cardRepository.findById(id).orElseThrow(() -> new NotFoundException("Card not found"));
    }

    public Card changeStatus(UUID id, String status) {
        Card card = get(id);
        card.setStatus(status);
        return cardRepository.save(card);
    }
}
