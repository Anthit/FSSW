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
        //스토어에 넣기전에 순서 증가시키고 계정저장
    }

    @Override
    public Optional<Account> findById(Long num) {
        return Optional.ofNullable(store.get(num));
        //null이 반환될 가능성이 있는건 optional로 감싼다.
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return store.values().stream()
                .filter(account -> account.getEmail().equals(email))
                .findAny();
        //루프를 다 돌면서 하나 찾아지면 그녀석을 반환
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
