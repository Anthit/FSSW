package com.ssw.fssw.repository;

import com.ssw.fssw.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryAccountRepository implements AccountRepository{
    private static Map<Long, Account> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Account save(Account account) {
        account.setNum(++sequence);
        store.put(sequence, account);
        return account;
    }

    @Override
    public Optional<Account> findById(Long num) {
        return Optional.ofNullable(store.get(num));
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return store.values().stream()
                .filter(account -> account.getEmail().equals(email))
                .findAny();
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
