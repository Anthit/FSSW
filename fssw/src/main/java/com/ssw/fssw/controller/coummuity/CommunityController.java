package com.ssw.fssw.controller.coummuity;


import com.ssw.fssw.domain.Comment;
import com.ssw.fssw.domain.Community;
import com.ssw.fssw.repository.CommentApiRepository;
import com.ssw.fssw.repository.CommunityApiRepository;
import com.ssw.fssw.service.CommentService;
import com.ssw.fssw.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/community")
public class CommunityController {


    private final CommunityService communityService;
    private final CommunityApiRepository communityApiRepository;
    private final CommentService commentService;

    @GetMapping
    public String communityList(Model model, @PageableDefault Pageable pageable ,@RequestParam(required = false,defaultValue = "") String search) {
//        Page<Community> communityList = communityApiRepository.findAll(pageable);
        Page<Community> communityList = communityApiRepository.findByTitleContainingOrContentsContaining(search,search,pageable);
        int startPage = Math.max(1, communityList.getPageable().getPageNumber() - 4); //현재 페이지 넘버를 가져온다.
        int endPage = Math.min(communityList.getTotalPages(), communityList.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("data", communityList);
        return "view/board/comList";
    }

    @GetMapping("/comWrite")
    public String comWrite(Model model) {
        model.addAttribute("comForm", new CommunityForm());
        return "view/board/comWrite";
    }

    @PostMapping("/comWrite")
    public String saveWrite(CommunityForm form) {

        Community community = new Community();
        community.setTitle(form.getTitle());
        community.setContents(form.getContent());
        community.setCategory(form.getCategory());

        communityService.saveCommunity(community);
        return "redirect:/community";
    }

    @GetMapping("/{id}/comDetail")
    public String pageDetail(@PathVariable("id") Long id, Model model) {
        Community community = communityService.findOne(id);

        CommunityForm form = new CommunityForm();
        form.setTitle(community.getTitle());
        form.setContent(community.getContents());


        model.addAttribute("community", community);
        // comment 부분

        List<Comment> commentList = commentService.commentList();
        model.addAttribute("comments", commentList);
        return "view/board/comDetail";
    }

    @GetMapping("/{id}/comModify")
    public String updateModifyForm(@PathVariable("id") Long id, Model model) {
        Community community = communityService.findOne(id);

        CommunityForm form = new CommunityForm();
        form.setTitle(community.getTitle());
        form.setContent(community.getContents());

        model.addAttribute("updateform", form);


        return "view/board/comModify";
    }

    @PostMapping("/{id}/comModify")
    public String updateModify(@PathVariable Long id, @ModelAttribute("updateform") CommunityForm form) {

        communityService.updateCommunity(id, form.getTitle(), form.getContent());

        return "redirect:/community/{id}/comDetail";
    }


    @GetMapping("/{id}/delete")
    public String deleteCommunity(@PathVariable("id") Long id) {
        communityApiRepository.deleteById(id);
        return "redirect:/community";
    }
}