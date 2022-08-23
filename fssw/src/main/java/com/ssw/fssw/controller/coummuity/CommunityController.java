package com.ssw.fssw.controller.coummuity;


import com.ssw.fssw.domain.Community;
import com.ssw.fssw.repository.CommunityApiRepository;
import com.ssw.fssw.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping
    public String communityList(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("data", communityApiRepository.findAll(PageRequest.of(page, 5)));
        model.addAttribute("currentPage", page);
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
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Community community = communityService.findOne(id);

        CommunityForm form = new CommunityForm();
        form.setTitle(community.getTitle());
        form.setContent(community.getContents());

        model.addAttribute("community", community);
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

        communityService.updateCommunity(id,form.getTitle(),form.getContent());

        return "redirect:/community/{id}/comDetail";
    }

    @GetMapping("/{id}/delete")
    public String deleteCommunity(@PathVariable("id") Long id){
        communityService.delete(id);
        return "redirect:/community";
    }
}
