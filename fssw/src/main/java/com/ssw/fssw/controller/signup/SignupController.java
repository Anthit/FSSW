package com.ssw.fssw.controller.signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {
    @RequestMapping("")
    public String signup(){
        return "view/signup/signup";
    }
}
