package com.ssw.fssw.controller.curriculum;

import com.ssw.fssw.service.search.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/curriculum")
public class CurriculumController {
    private final SearchService searchService;
    @Autowired
    public CurriculumController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public String curriculum(Model model) throws Exception {
//        List<Map.Entry<String, Long>> list = searchService.extractKeyword();
//        model.addAttribute("lists", list);
        return "view/curriculum/curriculum";
    }
}
