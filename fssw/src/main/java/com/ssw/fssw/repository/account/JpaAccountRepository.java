package com.ssw.fssw.repository.account;

import com.ssw.fssw.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
//import com.ssw.fssw.domain.CustomUserDetails;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class JpaAccountRepository implements AccountRepository{
    private final EntityManager em;

    public JpaAccountRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Account save(Account account) {
        log.info("JpaAccountRepository save !!!");
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

    public Optional<Account> findByEmailForValidEmail(String email) {
        return em.createQuery("select a from Account a where a.email=:email", Account.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findAny();
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
