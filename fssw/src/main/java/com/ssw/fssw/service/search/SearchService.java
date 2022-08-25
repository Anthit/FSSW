package com.ssw.fssw.service.search;

import com.ssw.fssw.domain.GoogleUrl;
import com.ssw.fssw.repository.elasticsearch.SearchRepository;

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
        return searchRepository.getGoogleUrlList(keyword);
    }
}
