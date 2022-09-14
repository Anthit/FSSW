package com.ssw.fssw.controller.signup;

import com.ssw.fssw.domain.User;
import com.ssw.fssw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;
    @GetMapping
    public String signup(Model model){
        model.addAttribute("signupForm",new SignupForm());
        return "view/signup/signup";
    }
    @PostMapping
    public String savedSignup(SignupForm form){
        User user = new User();
        user.setEmailId(form.getEmailId());
        user.setNickname(form.getNickname());
        user.setPassword(form.getPassword());
        userService.saveUser(user);
        return "redirect:/";
    }
}
