package com.ssw.fssw.service;

import com.ssw.fssw.domain.Account;
import com.ssw.fssw.repository.account.AccountRepository;
import com.ssw.fssw.service.account.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class AccountServiceIntegrateTest {
    @Autowired
    AccountService accountService;
    @Autowired AccountRepository accountRepository;

//    @BeforeEach
//    public void beforeEach(){
//        accountRepository = new MemoryAccountRepository();
//        accountService = new AccountService(accountRepository);
//    }

//    @AfterEach
//    public void afterEach(){
//        accountRepository.clearStore();
//    }

    @Test
    void 회원가입() {
        //given
        Account account = new Account();
//        Account account = new Account(1L, "email", "pw", "nick");
//        account.setEmail("spring");

        //when
        long saveNum = accountService.join(account);

        //then
        Account findAccount = accountService.findOne(saveNum).get();
        assertThat(account.getEmail()).isEqualTo(findAccount.getEmail());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Account account1 = new Account();
//        Account account1 = new Account(1L, "email1", "pw1", "nick1");
//        account1.setEmail("spring");
        Account account2 = new Account();
//        Account account2 = new Account(1L, "email1", "pw1", "nick1");
//        account2.setEmail("spring");

        // when
        accountService.join(account1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> accountService.join(account2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*        try{
            accountService.join(account2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/


        //then
    }

}