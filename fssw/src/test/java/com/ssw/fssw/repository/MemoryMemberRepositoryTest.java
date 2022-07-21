package com.ssw.fssw.repository;

import com.ssw.fssw.domain.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryAccountRepository repository = new MemoryAccountRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }


    @Test
    public void save(){
        Account account = new Account();
        account.setEmail("email");
        repository.save(account);
        Account result = repository.findById(account.getNum()).get();
        System.out.println("result : " + (result == account));
        assertThat(account).isEqualTo(result);
    }

    @Test
    public void findByEmail(){
        Account account1 = new Account();
        account1.setEmail("Spring1");
        repository.save(account1);

        Account account2 = new Account();
        account2.setEmail("Spring2");
        repository.save(account2);

        Account result = repository.findByEmail("Spring1").get();
        assertThat(result).isEqualTo(account1);
    }

    @Test
    public void findAll(){
        Account account1 = new Account();
        account1.setEmail("Spring1");
        repository.save(account1);
        Account account2 = new Account();
        account2.setEmail("Spring2");
        repository.save(account2);
        Account account3 = new Account();
        account3.setEmail("Spring3");
        repository.save(account3);

        List<Account> result = repository.findAll();

        assertThat(result.size()).isEqualTo(3);

    }
}
