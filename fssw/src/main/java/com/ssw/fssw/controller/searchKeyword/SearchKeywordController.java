package com.ssw.fssw.controller.searchKeyword;

import com.ssw.fssw.domain.GoogleUrl;
import com.ssw.fssw.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/searchKeyword")
public class SearchKeywordController {
    private final SearchService searchService;

    public SearchKeywordController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/{keyword}")
    public String searchKeyword(@PathVariable("keyword") String keyword, Model model, HttpServletRequest request) throws Exception {
        List<GoogleUrl> urlList = searchService.showGoogleSearchResult(keyword);
        model.addAttribute("urlList", urlList);

        HttpSession session = request.getSession();

        String refer = request.getHeader("referer");

        List<String> keywordList;
        if((keywordList = (List<String>)session.getAttribute("keywordList")) != null){

            if (refer.equals("http://localhost:8080/curriculum"))
                keywordList.clear();

            if (keywordList.contains(keyword)) {
                int endPoint = keywordList.indexOf(keyword);
                keywordList = keywordList.subList(0, endPoint+1);
            } else {
                keywordList.add(keyword);
            }
        } else {
            keywordList = new ArrayList<>();
            keywordList.add(keyword);
        }

        session.setAttribute("keywordList", keywordList);
        return "view/searchKeyword/searchKeyword";
    }
}