package com.ssw.fssw.controller.signup;

import com.ssw.fssw.domain.Account;
import com.ssw.fssw.repository.JpaAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignupController {
    private final JpaAccountRepository JpaAccountRepository;

    @RequestMapping("")
    public String signup(){
        return "view/signup/signup";
    }

    @RequestMapping
    public String JoinForm(@RequestBody Map<String, String> map){
        Account account = new Account();
        account.setNick(map.get("nickname"));
        account.setPw(map.get("pwd"));
        account.setEmail(map.get("email"));
        /*
        해당 서비스.저장하는 값()
        int cnt = accountRepository.isUsed(account);
        레포즈토리에 jpa를 통해 중복검사
        이메일을 where절에 넣고 이 이메일이 db에 있냐 jpa를 통해 물어보면
        결과값이 반환이 됐으면
        if(em.select () == null) -> count = 0;
        else -> count =1
        return count
        return Integer.toString(cnt);
         */
        return account.getNick();
    }
    @ResponseBody
    @PostMapping(name ="/signupEmail", produces = "application/json; charset= UTF-8")
    public String DuplicateEmailCheck(@RequestBody Map<String,String> map){
        Account account = new Account();
        account.setPw(map.get("pwd"));
        String email= JpaAccountRepository.findByEmail(account.setEmail(map.get("email"));
        return email;
    }

    @ResponseBody
    @PostMapping(name ="/signupNick", produces = "application/json; charset= UTF-8")
    public String DuplicateNickCheck(@RequestBody Map<String,String> map){
        Account account = new Account();
        account.setPw(map.get("pwd"));
        String email= JpaAccountRepository.findByNick(account.setNick(map.get("nickname"));
        return email;
    }

}
