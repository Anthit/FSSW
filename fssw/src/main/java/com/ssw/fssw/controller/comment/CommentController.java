package com.ssw.fssw.controller.comment;

import com.ssw.fssw.domain.Comment;
import com.ssw.fssw.domain.Community;
import com.ssw.fssw.repository.CommentApiRepository;
import com.ssw.fssw.service.CommentService;
import com.ssw.fssw.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        comment.setText(map.get("contentvalue"));
        Community community = communityService.findOne(Long.parseLong(map.get("boardId")));
        comment.setCommunity(community);
        //추가 구현 필요
        //그룹 추가
        comment.setGroup(Integer.parseInt(map.get("reCommentGroup")));
        //오더 추가
        comment.setOrder(Integer.parseInt(map.get("reCommentFloor")));
        //클래스 추가
        comment.setFloor(Integer.parseInt(map.get("reCommentOrder")));


        commentService.saveComment(comment);

        return map.get("contentvalue");
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


    @PostMapping("/updatecomment")
    public String update(@RequestParam("Commentcontent") String UpdatedText, @RequestParam("comment-id") Long id) {
        Comment comment;
        comment=commentService.findOne(id);

        comment.setText(UpdatedText);
        commentApiRepository.save(comment);
        return "redirect:/community/{id}/comDetail";
    }
}
