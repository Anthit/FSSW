package com.ssw.fssw.controller.roadmap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roadmap")
public class RoadmapController {
    @RequestMapping("")
    public String roadmap(){
        return "view/roadmap/roadmap";
    }
}
