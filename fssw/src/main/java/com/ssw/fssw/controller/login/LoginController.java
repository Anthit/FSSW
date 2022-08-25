package com.ssw.fssw.controller.login;

import com.ssw.fssw.domain.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class LoginController {
    @GetMapping("/main/login")
    public String login(HttpServletRequest request){
        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("referer", referer);
        return "view/login/login";
    }

    @GetMapping("/main/login.fail")
    public String loginFail(){
        return "view/login/loginfail";
    }
}