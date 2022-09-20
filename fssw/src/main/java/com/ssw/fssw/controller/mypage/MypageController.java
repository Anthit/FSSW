package com.ssw.fssw.controller.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MypageController {
    @GetMapping
    public String myPage(){
        return "view/mypage/mypage";
    }

    @RequestMapping("/mypageEdit")
    public String mypageEdit(){
        return "view/mypage/mypageEdit";
    }
}
