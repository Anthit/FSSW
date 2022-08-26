package com.ssw.fssw.controller.login.curriculum;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/curriculum")
public class CurriculumController {
    @RequestMapping("")
    public String curriculum(){
        return "view/curriculum/ curriculum";
    }
}
