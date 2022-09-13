package com.ssw.fssw.repository;

import com.ssw.fssw.domain.Account;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaAccountRepository {
    private final EntityManager em;

    public JpaAccountRepository(EntityManager em) {
        this.em = em;
    }

    public Account save(Account account) {
        em.persist(account);
        return account;
    }

    public Optional<Account> findById(Long num) {
        Account account = em.find(Account.class, num);
        return Optional.ofNullable(account);
    }

    public String findByEmail(String email) {
        List<Account> result = em.createQuery("select a from Account a where a.email=:email", Account.class)
                .setParameter("email", email)
                .getResultList();
        int count;
        if(result != null){
            count =0;
        }
        else count =1;
        return Integer.toString(count);
        //return result.stream().findAny();
    }
    public String findByNick(String nick) {
        List<Account> result = em.createQuery("select a from Account a where a.nick=:nick", Account.class)
                .setParameter("nick", nick)
                .getResultList();
        int count;
        if(result != null){
            count =0;
        }
        else count =1;
        return Integer.toString(count);
        //return result.stream().findAny();
    }


/*    @Override
    public List<Account> findAll() {
        return em.createQuery("select a from Account as a", Account.class)
                .getResultList();
    }*/
}
