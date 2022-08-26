package com.ssw.fssw.controller.findteam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/findteam")
public class FindTeamController {
    @RequestMapping("")
    public String findTeam(){

        return "view/findTeam/findList";
    }

    @RequestMapping("/findWrite")
    public String findTeamWrite(){

        return "view/findTeam/findWrite";
    }
}
