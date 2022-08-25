package com.ssw.fssw.repository.elasticsearch;

import com.ssw.fssw.domain.GoogleUrl;

import java.util.List;
import java.util.Map;

public interface SearchRepository {
    List<Map.Entry<String, Long>> search() throws Exception;

    List<GoogleUrl> getGoogleUrlList(String keyword) throws Exception;
}
