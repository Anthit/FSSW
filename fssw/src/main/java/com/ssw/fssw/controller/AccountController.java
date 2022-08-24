package com.ssw.fssw.controller;

import com.ssw.fssw.domain.Account;
import com.ssw.fssw.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // url에 그냥 치고 들어오는 방식을 get 방식이라고 여길 타고 들어와서 단순히 createMemberForm 파일을 보여줌
    @GetMapping("/signup")
    public String createForm(){
        return "view/signup/signup";
    }

    // form 태그에서 method=post 인것을 submit 버튼 눌렀을 때 이 경로가 호출
    @PostMapping("/signup")
    public String create(MemberForm form){
        Account account = new Account();
        account.setNick(form.getEmail());
        accountService.join(account);

/*        Account accountEmail = new Account();
        accountEmail.setEmail(form.getEmail());
        accountService.join(accountEmail);*/
        return "redirect:/login";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Account> accounts = accountService.findAccounts();
        model.addAttribute("members", accounts);
        return "members/memberList";
    }
}
