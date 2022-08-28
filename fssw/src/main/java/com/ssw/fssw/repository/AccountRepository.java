package com.ssw.fssw.repository;

import com.ssw.fssw.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    //회원저장시 저장한 회원을 반환
    Optional<Account> findById(Long num);
    //id로 회원을 찾는것
    Optional<Account> findByEmail(String email);
    //List<Account> findAll();
    //지금까지 저장된 모든 회원리스트 반환
}
