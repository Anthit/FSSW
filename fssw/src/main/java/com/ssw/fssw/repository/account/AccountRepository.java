package com.ssw.fssw.repository.account;

import com.ssw.fssw.domain.Account;
import org.springframework.stereotype.Repository;
//import com.ssw.fssw.domain.CustomUserDetails;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findById(Long num);
    Optional<Account> findByEmail(String email);
    Optional<Account> findByEmailForValidEmail(String email);
//    Optional<CustomUserDetails> loadUserByUsername(String email);
    List<Account> findAll();

}
