package com.example.cms.service;

import com.example.cms.model.Account;
import com.example.cms.repository.AccountRepository;
import com.example.cms.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(Account account) {
        return accountRepository.save(account);
    }

    public Account get(UUID id) {
        return accountRepository.findById(id).orElseThrow(() -> new NotFoundException("Account not found"));
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account update(UUID id, Account updated) {
        Account existing = get(id);
        existing.setStatus(updated.getStatus());
        existing.setBalance(updated.getBalance());
        return accountRepository.save(existing);
    }

    public void delete(UUID id) {
        accountRepository.deleteById(id);
    }
}
