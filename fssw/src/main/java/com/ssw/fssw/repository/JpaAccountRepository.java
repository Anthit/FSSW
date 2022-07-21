package com.ssw.fssw.repository;

import com.ssw.fssw.domain.Account;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaAccountRepository implements AccountRepository{
    private final EntityManager em;

    public JpaAccountRepository(EntityManager em) {
        this.em = em;
    }

    @Override
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
        List<Account> result = em.createQuery("select a from Account a where a.email=:email", Account.class)
                .setParameter("email", email)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Account> findAll() {
        return em.createQuery("select a from Account as a", Account.class)
                .getResultList();
    }
}
