package com.ssw.fssw;

import com.ssw.fssw.repository.AccountRepository;
import com.ssw.fssw.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final AccountRepository accountRepository;

    @Autowired
    public SpringConfig(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Bean
    public AccountService accountService(){
        return new AccountService(accountRepository);
    }

    
    // Aop에 직접 @Component를 이용해서 Component scan으로 사용해도 되고 이 방식으로 사용해도 됨
    // 이 방식으로 하면 한가지를 더 해줘야함
    // 이 프로젝트에선 Component scan 방식으로 진행
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }



//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
//    @Bean
//    public AccountRepository accountRepository(){
//        return new JpaAccountRepository(em);
//    }
}
