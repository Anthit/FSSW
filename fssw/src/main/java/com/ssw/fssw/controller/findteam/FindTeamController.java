package com.ssw.fssw.controller.findteam;

import com.ssw.fssw.controller.comment.CommentForm;
import com.ssw.fssw.controller.coummuity.CommunityForm;
import com.ssw.fssw.domain.Comment;
import com.ssw.fssw.domain.Community;
import com.ssw.fssw.repository.CommentApiRepository;
import com.ssw.fssw.repository.CommunityApiRepository;
import com.ssw.fssw.service.CommentService;
import com.ssw.fssw.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("/findteam")
public class FindTeamController {
    private final CommunityService communityService;
    private final CommunityApiRepository communityApiRepository;

    private final CommentService commentService;

    private final CommentApiRepository commentApiRepository;

    @GetMapping
    public String findTeamList(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(required = false, defaultValue = "") String search) {
        Page<Community> communityList = communityApiRepository.findByTitleContainingOrContentsContaining(search, search, pageable);
        int startPage = Math.max(1, communityList.getPageable().getPageNumber() - 4); //현재 페이지 넘버를 가져온다.
        int endPage = Math.min(communityList.getTotalPages(), communityList.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("data", communityList);
        return "view/findTeam/findList";
    }

    @GetMapping("/findWrite")
    public String findTeamWrite(Model model) {
        model.addAttribute("findForm", new FindTeamForm());
        return "view/findTeam/findWrite";
    }

    @PostMapping("/findWrite")
    public String saveFindTeamWrite(FindTeamForm form) {

        Community community = new Community();
        community.setTitle(form.getTitle());
        community.setContents(form.getContent());
        community.setDate(form.getLocalDateTime().now());
        community.setCategory(form.getCategory());
        community.setNum(form.getFindTeam_num());

        communityService.saveCommunity(community);
        return "redirect:/findteam";
    }
    @GetMapping("/{id}/findDetail")
    public String findTeamPageDetail(@PathVariable("id") Long id, Model model) {
        //community 부분
        Community community = communityService.findOne(id);
        FindTeamForm form = new FindTeamForm();
        form.setTitle(community.getTitle());
        form.setContent(community.getContents());
        form.setLocalDateTime(community.getDate().now());

        model.addAttribute("community", community);

        // commentList 부분
        List<Comment> commentList = commentService.commentList(id);


        //댓글 작성부분
        CommentForm commentForm = new CommentForm();
        Long commentLastId = commentApiRepository.getCommentLastId();
        commentForm.setId(commentLastId.longValue());

        //대댓글 작성 부분
        commentForm.setOrder(commentApiRepository.getCommentLastOrder());
        model.addAttribute("comment",commentForm);
        model.addAttribute("comments", commentList);

        return "view/findTeam/findDetail";
    }

    @GetMapping("/{id}/findModify")
    public String updateModifyFindTeamForm(@PathVariable("id") Long id, Model model) {
        Community community = communityService.findOne(id);

        FindTeamForm form = new FindTeamForm();
        form.setTitle(community.getTitle());
        form.setContent(community.getContents());

        model.addAttribute("updateFindForm", form);

        return "view/findTeam/findModify";
    }
    @PostMapping("/{id}/findModify")
    public String updateFindTeamModify(@PathVariable Long id, @ModelAttribute("updateFindForm") FindTeamForm form) {

        communityService.updateCommunity(id, form.getTitle(), form.getContent());

        return "redirect:/findteam/{id}/findDetail";
    }
    @GetMapping("/{id}/delete")
    public String deleteFindTeam(@PathVariable("id") Long id) {
        communityApiRepository.deleteById(id);
        return "redirect:/findteam";
    }
}
