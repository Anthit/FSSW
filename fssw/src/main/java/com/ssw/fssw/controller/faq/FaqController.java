package com.ssw.fssw.controller.faq;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/faq")
public class FaqController {
    @RequestMapping("")
    public String faq(){
        return "view/faq/faq";
    }
}
