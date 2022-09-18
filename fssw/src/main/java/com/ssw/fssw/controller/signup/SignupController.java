package com.ssw.fssw.controller.signup;

import com.ssw.fssw.domain.Account;
import com.ssw.fssw.service.account.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/main/signup")
public class SignupController {
    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public String signup(){
        return "view/signup/signup";
    }

    @PostMapping("")
    public String postSignup(Account account){
        log.info("email : " + account.getEmail());
        log.info("pw : " + account.getPw());
        log.info("nick : " + account.getNick());
        BCryptPasswordEncoder pwencoder = new BCryptPasswordEncoder();
        String currentpw = account.getPw();
        String encodedpw = pwencoder.encode(currentpw);
        account.setPw(encodedpw);
//        account = new Account(account.getNum(), account.getEmail(), encodedpw, account.getNick());
//
        long num = accountService.join(account);
        log.info("num : " + num);
        return "redirect:/main/login";
    }
}
