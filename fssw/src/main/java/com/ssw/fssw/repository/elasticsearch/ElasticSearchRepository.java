package com.ssw.fssw.repository.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.ssw.fssw.domain.GoogleUrl;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

import static org.jsoup.Jsoup.connect;

@Slf4j
public class ElasticSearchRepository implements SearchRepository{
    private final ElasticsearchClient client;

    public ElasticSearchRepository(ElasticsearchClient client) {
        this.client = client;
    }

    public List<Map.Entry<String, Long>> search() throws Exception{

        SearchResponse<Object> search;
        String[] keyword = {"가", "나", "다", "라", "마", "바", "사", "자"};
        Map<String, Long> keyworkCountMap = new HashMap<>();
        for(int i = 0; i < keyword.length; i++){
            int finalI = i;
            search = client.search(s -> s
                            .index("filebeat-7.12.0-2022.07.29.07.33")
                            .size(0)
                            .query(q -> q
                                    .match(t -> t
                                            .field("filename.keyword")
                                            .query("c언어") // 구글에 검색할 키워드
                                    ))
                            .postFilter(p -> p
                                    .queryString(qs -> qs
                                            .query(keyword[finalI]) // 구글 검색 결과 문서들에 이 단어가 포함된 문서 개수를 찾기 위한 키워드
                                            .fields("message")
                                    )
                            ),
                    Object.class);
            keyworkCountMap.put(keyword[i], search.hits().total().value());
        }
        List<Map.Entry<String, Long>> mapList = new LinkedList<>(keyworkCountMap.entrySet());
        mapList.sort(
                ( (o1, o2) -> (int) (keyworkCountMap.get(o2.getKey()) - keyworkCountMap.get(o1.getKey())))
        );
        mapList = mapList.subList(0, 5);
        return mapList;
    }

    @Override
    public List<GoogleUrl> getGoogleUrlList(String keyword) throws Exception {
        String baseUrl ="https://www.google.com/search?q="+keyword+"&lr=lang_ko";
        Document baseDocument = connect(baseUrl).get();

        Elements es = baseDocument.select("div.jtfYYd");

        GoogleUrl sl;
        List<GoogleUrl> list = new ArrayList<>();
        for(Element e : es){
            String url = e.child(0).select("div.yuRUbf > a").attr("href");
            String title = e.child(0).select("div.yuRUbf > a > h3").text();
            String content = e.child(1).select("span").text();
            sl = new GoogleUrl(url, title, content);
            list.add(sl);
        }
        return list;
    }
}