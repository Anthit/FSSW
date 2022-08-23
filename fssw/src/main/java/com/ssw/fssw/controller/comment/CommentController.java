package com.ssw.fssw.controller.comment;

import com.ssw.fssw.domain.Comment;
import com.ssw.fssw.domain.Community;
import com.ssw.fssw.service.CommentService;
import com.ssw.fssw.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommunityService communityService;

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

}
