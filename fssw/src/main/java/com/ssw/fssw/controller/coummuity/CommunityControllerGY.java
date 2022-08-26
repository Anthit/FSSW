package com.ssw.fssw.controller.community;

import com.ssw.fssw.domain.Board;
import com.ssw.fssw.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/board/community")
public class CommunityControllerGY {
    @Autowired
    private BoardService boardService;
    @GetMapping("")
    public String showList(){

        return "view/community/comList";
    }
    @GetMapping("/write")
    public String showWrite(){
        return "view/community/comWrite";
    }
    @PostMapping("/write")
    public String write(Board form){
        log.info("form : " + form.getTitle() + " , " + form.getContent() + " , " + form.getCategory());
        form.setNick("nick");
        form.setType('0');
        boardService.post(form);
        return "redirect:/community";
    }
}
