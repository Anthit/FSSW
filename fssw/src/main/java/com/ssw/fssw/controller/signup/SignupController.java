package com.ssw.fssw.controller.signup;

import com.ssw.fssw.domain.Account;
import com.ssw.fssw.repository.account.JpaAccountRepository;
import com.ssw.fssw.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
//@RequestMapping("/main/signup")
@RequiredArgsConstructor
public class SignupController {
    //@Autowired
    //private AccountService accountService;

    private final JpaAccountRepository JpaAccountRepository;

    @GetMapping("/main/signup")
    public String signup(){
        return "view/signup/signup";
    }
//    @RequestMapping
//    public String JoinForm(@RequestBody Map<String, String> map){
//        Account account = new Account();
//        account.setNick(map.get("nickname"));
//        account.setPw(map.get("pwd"));
//        account.setEmail(map.get("email"));
//        /*
//        해당 서비스.저장하는 값()
//        int cnt = accountRepository.isUsed(account);
//        레포즈토리에 jpa를 통해 중복검사
//        이메일을 where절에 넣고 이 이메일이 db에 있냐 jpa를 통해 물어보면
//        결과값이 반환이 됐으면
//        if(em.select () == null) -> count = 0;
//        else -> count =1
//        return count
//        return Integer.toString(cnt);
//         */
//        return account.getNick();
//    }

    @PostMapping("/main/signup")
    public String signupDo(){
        return "";
    }

    @ResponseBody
    @RequestMapping(value="/signupEmail", produces = "application/text; charset=UTF-8", method = RequestMethod.POST)
    public String DuplicateEmailCheck(@RequestBody Map<String,String> map){
        Account account = new Account();
        account.setPw(map.get("pwd"));
        account.setEmail(map.get("email"));
        String email= JpaAccountRepository.findByEmail2(account.getEmail());
        return email;
    }

    @RequestMapping(value = "/signupEmail", method = RequestMethod.GET)
    public String signupEmailTest(){
        return "view/main/main";
    }

    /*@ResponseBody
    @PostMapping(name ="/signupNick", produces = "application/json; charset= UTF-8")
    public String DuplicateNickCheck(@RequestBody Map<String,String> map){
        Account account = new Account();
        account.setPw(map.get("pwd"));
        account.setNick(map.get("nickname"));
        String nick= JpaAccountRepository.findByNick2(account.getNick());
        return nick;
    }*/
//
//    @PostMapping("")
//    public String postSignup(Account account){
//        log.info("email : " + account.getEmail());
//        log.info("pw : " + account.getPw());
//        log.info("nick : " + account.getNick());
//        BCryptPasswordEncoder pwencoder = new BCryptPasswordEncoder();
//        String currentpw = account.getPw();
//        String encodedpw = pwencoder.encode(currentpw);
//        account.setPw(encodedpw);
////        account = new Account(account.getNum(), account.getEmail(), encodedpw, account.getNick());
////
//        accountService.join(account);
//        return "redirect:/main/login";
//    }
}
