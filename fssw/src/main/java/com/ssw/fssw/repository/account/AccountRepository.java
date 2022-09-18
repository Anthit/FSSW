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
    String findByEmail2(String email);
    String findByNick2(String nick);
    Optional<Account> findByEmailForValidEmail(String email);
//    Optional<CustomUserDetails> loadUserByUsername(String email);
    List<Account> findAll();

}
