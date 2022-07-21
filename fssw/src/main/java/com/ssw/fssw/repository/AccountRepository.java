package com.ssw.fssw.repository;

import com.ssw.fssw.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findById(Long num);
    Optional<Account> findByEmail(String email);
    List<Account> findAll();

}
