package com.ssw.fssw.elasticsearch;

import com.ssw.fssw.service.search.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class ElasticsearchConfigTest {
    @Autowired
    SearchService searchService;

    @Test
    public void 검색() throws Exception {
        for (Map.Entry entry : searchService.extractKeyword()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

}