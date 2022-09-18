package com.ssw.fssw.service.account;

import com.ssw.fssw.domain.Account;
import com.ssw.fssw.repository.account.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Slf4j
@Transactional
public class AccountService {
    @Autowired
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public long join(Account account){
        // 같은 이메일을 가진 중복 회원 X
        log.info("AccountService join !!!");
        validateDuplicateAccount(account);
        accountRepository.save(account);
        return account.getNum();
    }

    private void validateDuplicateAccount(Account account) {
        accountRepository.findByEmailForValidEmail(account.getEmail())
                .ifPresent(a -> {
                    log.info("failed findByEmail : " + a );
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        log.info("validateDuplicateAccount complete!!!");
    }

    public List<Account> findAccounts(){
        return accountRepository.findAll();
    }
//    public Optional<Account> findOne(Long accountNum){
//        return accountRepository.findById(accountNum);
//    }
}
