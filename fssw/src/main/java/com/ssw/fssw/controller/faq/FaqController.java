package com.ssw.fssw.controller.faq;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/faq")
public class FaqController {
    @GetMapping
    public String faq(){
        return "view/faq/faq";
    }
}
