package com.ssw.fssw.service.security;

import com.ssw.fssw.domain.Account;
import com.ssw.fssw.repository.account.AccountRepository;
import com.ssw.fssw.service.account.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String email = authentication.getName();
        Account account = accountRepository.findByEmail(email).get();
        HttpSession session = request.getSession();
        session.setAttribute("nickname", account.getNick());

        log.info("Login Success !!!");
        String url = Optional.ofNullable(
                (String) request.getSession().getAttribute("referer")
                ).orElse("/main");
        log.info("referer url : " + url);
        url = (
                url.equals("http://localhost:8080/main/signup")
                || url.equals("http://localhost:8080/main/login")
                || url.equals("http://localhost:8080/main/login.fail")
        ) ? "/main" : url;
        response.sendRedirect(url);
    }
}
