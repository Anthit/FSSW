package com.ssw.fssw.controller.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MypageController {
    @RequestMapping("")
    public String mypage(){
        return "view/mypage/mypage";
    }

    @RequestMapping("/mypageEdit")
    public String mypageEdit(){
        return "view/mypage/mypageEdit";
    }
}
