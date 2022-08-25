package com.ssw.fssw.controller.comment;

import com.ssw.fssw.domain.Comment;
import com.ssw.fssw.domain.Community;
import com.ssw.fssw.repository.CommentApiRepository;
import com.ssw.fssw.service.CommentService;
import com.ssw.fssw.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community/{id}/comDetail")
public class CommentController {

    private final CommentService commentService;
    private final CommunityService communityService;

    private final CommentApiRepository commentApiRepository;

    @ResponseBody
    @PostMapping(produces = "application/json; charset=UTF-8")
    public String saveComment(@RequestBody Map<String, String> map) {
        Comment comment = new Comment();
        comment.setText(map.get("content"));
        Community community = communityService.findOne(Long.parseLong(map.get("boardId")));
        comment.setCommunity(community);

        commentService.saveComment(comment);

        return map.get("content");
    }

    @GetMapping("/delete")
    public String deleteCommunity(long id){
        commentApiRepository.deleteById(id);
        return "redirect:/community/{id}/comDetail";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Comment findOne(long id) {
        return commentApiRepository.findById(id).get();
    }

    @PostMapping("/save")
    public String save(Comment comment) {
        commentApiRepository.save(comment);
        return "redirect:/community/{id}/comDetail";
    }
}
