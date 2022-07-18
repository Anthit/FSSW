package com.ssw.fssw.controller.hotissues;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotKeyword")
public class HotIssuesController {
    @RequestMapping("")
    public String hotKeyword(){
        return "view/hotKeyword/hotKeyword";
    }
}
