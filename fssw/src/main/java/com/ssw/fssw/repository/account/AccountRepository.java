package com.ssw.fssw.repository.account;

import com.ssw.fssw.domain.Account;
//import com.ssw.fssw.domain.CustomUserDetails;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findById(Long num);
    Optional<Account> findByEmail(String email);
    Optional<Account> findByNick(String nick);
    String findByEmail2(String email);
    String findByNick2(String nick);
//    Optional<CustomUserDetails> loadUserByUsername(String email);
    List<Account> findAll();

}
