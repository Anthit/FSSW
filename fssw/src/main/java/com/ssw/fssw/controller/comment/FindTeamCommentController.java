package com.ssw.fssw.controller.comment;


import com.ssw.fssw.domain.Comment;
import com.ssw.fssw.domain.Community;
import com.ssw.fssw.repository.CommentApiRepository;
import com.ssw.fssw.repository.CommentRepository;
import com.ssw.fssw.service.CommentService;
import com.ssw.fssw.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/findteam/{id}/findDetail")
public class FindTeamCommentController {

    private final CommentService commentService;
    private final CommunityService communityService;
    private final CommentApiRepository commentApiRepository;

    private final CommentRepository commentRepository;

    @ResponseBody
    @PostMapping(produces = "application/json; charset=UTF-8")
    public String saveComment(@RequestBody Map<String, String> map) {
        Comment comment = new Comment();
        comment.setText(map.get("contentvalue"));
        Community community = communityService.findOne(Long.parseLong(map.get("boardId")));
        comment.setCommunity(community);
        //댓글 저장 기능

        //그룹 추가
        comment.setGroup(Integer.parseInt(map.get("reCommentGroup")));
        //오더 추가
        comment.setOrder(Integer.parseInt(map.get("reCommentFloor")));
        //클래스 추가
        comment.setFloor(Integer.parseInt(map.get("reCommentOrder")));


        commentService.saveComment(comment);

        return map.get("contentvalue");
    }

    @ResponseBody
    @PostMapping(value = "/reComment", produces = "application/json; charset=UTF-8")
    public String saveReComment(@RequestBody Map<String, String> map) {
        Comment comment = new Comment();
        comment.setText(map.get("reComment-text"));
        Community community = communityService.findOne(Long.parseLong(map.get("re-boardId")));
        comment.setCommunity(community);
        //대댓글 저장 기능

        //그룹 추가 사항-> 현재 id의 group값을 받아와야함.
        comment.setGroup(Integer.parseInt(map.get("ano-reComment-group")));
        //오더 추가
        comment.setOrder(Integer.parseInt(map.get("ano-reComment-order")));
        //클래스 추가
        comment.setFloor(Integer.parseInt(map.get("ano-reComment-floor")));


        commentService.saveComment(comment);

        return map.get("reComment-text");
    }

    @GetMapping("/delete")
    public String deleteCommunity(long id) {
        Comment comment = commentService.findOne(id);
//        int updatedeletetext = commentApiRepository.updateDeleteText("삭제된 댓글입니다.");
//        if (comment.floor == 1 && commentService.groupList(id).size() > 1) {
//            comment.setText(String.valueOf(updatedeletetext));
        if(comment.floor==1){
            commentService.groupCommentDelete(id);
        }
        if(comment.floor==2) {
            commentApiRepository.deleteById(id);
        }
        // 수정해야함.
        return "redirect:/findteam/{id}/findDetail";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Comment findOne(long id) {
        return commentApiRepository.findById(id).get();
    }


    @PostMapping("/updatecomment")
    public String update(@RequestParam("Commentcontent") String UpdatedText, @RequestParam("comment-id") Long id) {
        Comment comment;
        comment = commentService.findOne(id);

        comment.setText(UpdatedText);
        commentApiRepository.save(comment);
        return "redirect:/findteam/{id}/findDetail";
    }
}
