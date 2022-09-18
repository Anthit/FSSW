package com.ssw.fssw.controller.roadmap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roadmap")
public class RoadmapController {
    @GetMapping
    public String roadmap(){
        return "view/roadmap/roadmap";
    }
}
