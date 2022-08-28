package com.ssw.fssw.service;

import com.ssw.fssw.domain.Account;
import com.ssw.fssw.repository.AccountRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;
    //회원서비스 개발을 위한 어카운트리포즈토리
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public long join(Account account){
        // 같은 이메일을 가진 중복 회원 X
        validateDuplicateAccount(account);
        accountRepository.save(account);
        return account.getNum();
    }

    private void validateDuplicateAccount(Account account) {
        accountRepository.findByEmail(account.getEmail())
                .ifPresent(a -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

    }

    public List<Account> findAccounts(){
        return accountRepository.findAll();
    }
    public Optional<Account> findOne(Long accountNum){
        return accountRepository.findById(accountNum);
    }
}
