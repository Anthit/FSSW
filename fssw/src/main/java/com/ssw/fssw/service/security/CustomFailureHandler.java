package com.ssw.fssw.service.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Service
public class CustomFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("loginFailId", request.getParameter("email"));
        log.warn("Login Fail!!!");
        log.warn("exception type : " + exception.getClass().getName());
        log.warn("exception message : " + exception.getMessage());
        response.sendRedirect("/main/login.fail");
    }
}
