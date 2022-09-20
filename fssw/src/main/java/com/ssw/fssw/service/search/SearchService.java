package com.ssw.fssw.service.search;

import com.ssw.fssw.domain.GoogleUrl;
import com.ssw.fssw.repository.elasticsearch.SearchRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchService {
    private final SearchRepository searchRepository;

    public SearchService(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public List<Map.Entry<String, Long>> extractKeyword() throws Exception {
        return searchRepository.search();
    }

    public List<GoogleUrl> showGoogleSearchResult(String keyword) throws Exception {
        List<GoogleUrl> googleUrls = searchRepository.getGoogleUrlList(keyword);
        if (googleUrls.size() > 5){
            googleUrls = googleUrls.subList(0, 5);
        }
        return googleUrls;
    }

    public List<String> stringResult(String keyword) {
        List<String> list = new ArrayList<>();
        switch (keyword) {
            case "C언어":
                list.add("절차지향");
                list.add("함수");
                list.add("printf");
                break;
            case "절차지향":
                list.add("C언어");
                list.add("객체지향");
                list.add("함수");
                break;
            case "객체지향":
                list.add("오버라이딩");
                list.add("상속");
                list.add("다형성");
                break;
            case "상속":
                list.add("오버라이딩");
                list.add("다형성");
                list.add("부모");
                break;
        }
        return list;
    }
}
