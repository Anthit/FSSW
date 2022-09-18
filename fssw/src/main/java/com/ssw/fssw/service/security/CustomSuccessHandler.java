package com.ssw.fssw.service.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
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
