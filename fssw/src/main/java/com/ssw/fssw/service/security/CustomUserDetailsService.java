package com.ssw.fssw.service.security;

import com.ssw.fssw.domain.Account;
import com.ssw.fssw.repository.account.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Account account = accountRepository.findByEmail(username).get();
        String auth =(!account.getEmail().equals("admin"))? "USER" : "ADMIN";

        return User.builder()
                .username(account.getEmail())
                .password(account.getPw())
                .authorities(new SimpleGrantedAuthority("ROLE_"+auth))
                .build();
    }
}
