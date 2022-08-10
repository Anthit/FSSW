package com.ssw.fssw.controller.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.annotation.WebServlet;

@Controller
@RequestMapping("/community")
@WebServlet(name = "CommunityController", urlPatterns = "/view/community/*")
public class CommunityController {
    @RequestMapping("")
    public String community(){
        return "view/community/comList";
    }
    @RequestMapping("/comWrite")
    public String communityWrite(){
        return "view/community/comWrite";
    }
    @RequestMapping("/comDetail")
   public String communityDetail(){
        return "";
    }
}
