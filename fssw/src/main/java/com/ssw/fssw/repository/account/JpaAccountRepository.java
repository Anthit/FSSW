package com.ssw.fssw.repository.account;

import com.ssw.fssw.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
//import com.ssw.fssw.domain.CustomUserDetails;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
public class JpaAccountRepository implements AccountRepository{
    private final EntityManager em;

    public JpaAccountRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public Account save(Account account) {
        em.persist(account);
        return account;
    }

    @Override
    public Optional<Account> findById(Long num) {
        Account account = em.find(Account.class, num);
        return Optional.ofNullable(account);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return Optional.ofNullable( em.createQuery("select a from Account a where a.email=:email", Account.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findAny()
                .orElseThrow(() -> new UsernameNotFoundException("유저 이메일이 " + email + "인 사용자를 찾을 수 없습니다.")));
    }

    @Override
    public Optional<Account> findByNick(String nick) {
        return Optional.empty();
    }


    /*   public Optional<Account> findById(Long num) {
           Account account = em.find(Account.class, num);
           return Optional.ofNullable(account);
       }*/
    @Override
    @Transactional
    public String findByEmail2(String email) {
        List<Account> result = em.createQuery("select a from Account a where a.email=:email", Account.class)
                .setParameter("email", email)
                .getResultList();

        //log.info(result.get(0).getEmail());
        int count;

        count=result.size()==0?0:1;
        return Integer.toString(count);
/*        if(result != null){
            count =0;
        }
        else count =1;*/
        //return result.stream().findAny();
    }

    @Override
    @Transactional
    public String findByNick2(String nick) {
        List<Account> result = em.createQuery("select a from Account a where a.nick=:nick", Account.class)
                .setParameter("nick", nick)
                .getResultList();
        int count;

        count=result.size()==0?0:1;
        return Integer.toString(count);
        //return result.stream().findAny();
    }




//    @Override
//    public Optional<CustomUserDetails> loadUserByUsername(String email) {
//        List<CustomUserDetails> result = em.createQuery("select a from Account a where a.email=:email", Account.class)
//                .setParameter("email", email)
//                .getResultList();
//        return result.stream().findAny();
//    }

    @Override
    public List<Account> findAll() {
        return em.createQuery("select a from Account as a", Account.class)
                .getResultList();
    }
}
