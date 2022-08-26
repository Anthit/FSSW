package com.ssw.fssw.controller.curriculum;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/curriculum")
public class CurriculumController {
    @RequestMapping("")
    public String curriculum(){
        return "view/curriculum/ curriculum";
    }
}
