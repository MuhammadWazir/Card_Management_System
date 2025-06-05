package com.example.cms.controller;

import com.example.cms.dto.CardRequest;
import com.example.cms.model.Card;
import com.example.cms.service.CardService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    @PostMapping
    public Card create(@RequestBody CardRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public Card get(@PathVariable UUID id) {
        return service.get(id);
    }

    @PatchMapping("/{id}/status")
    public Card changeStatus(@PathVariable UUID id, @RequestParam String status) {
        return service.changeStatus(id, status);
    }
}
