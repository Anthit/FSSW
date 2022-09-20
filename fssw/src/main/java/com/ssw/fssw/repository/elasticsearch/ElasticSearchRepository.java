package com.ssw.fssw.repository.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.ssw.fssw.domain.GoogleUrl;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.security.core.parameters.P;

import java.io.FileInputStream;
import java.util.*;

import static org.jsoup.Jsoup.connect;

@Slf4j
public class ElasticSearchRepository implements SearchRepository{
    private final ElasticsearchClient client;

    public ElasticSearchRepository(ElasticsearchClient client) {
        this.client = client;
    }

    public List<Map.Entry<String, Long>> search() throws Exception{
        try {
            FileInputStream file = null;
            if ((file = new FileInputStream("c:/Temp/keyword/elk_keyword.xlsx")) == null) {
                System.out.println("file is null");
                return null;
            } else {
                System.out.println("file is not null");
            }
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            int rowNo = 0;
            int cellIndex = 1;

            XSSFSheet sheet = workbook.getSheetAt(1);
            int rows = sheet.getPhysicalNumberOfRows();
            for (rowNo = 0; rowNo < rows; rowNo++) {
                XSSFRow row = sheet.getRow(rowNo);
                if (row != null) {
                    XSSFCell cell = row.getCell(cellIndex);
                    String keyword = cell.getStringCellValue() + "";
                    SearchResponse<Object> search;
                    Map<String, Long> keyworkCountMap = new HashMap<>();
                    search = client.search(s -> s
                                    .index("filebeat-7.14.0-2022.09.18.08.17")
                                    .size(0)
                                    .query(q -> q
                                            .match(t -> t
                                                    .field("filename")
                                                    .query(keyword)
                                            ))
                                    .postFilter(p -> p
                                            .queryString(qs -> qs
                                                    .fields("message")
                                                    .query(keyword)
                                            )
                                    ),
                            Object.class);
                    keyworkCountMap.put(keyword, search.hits().total().value());
                    List<Map.Entry<String, Long>> mapList = new LinkedList<>(keyworkCountMap.entrySet());
                    mapList.sort(
                            ( (o1, o2) -> (int) (keyworkCountMap.get(o2.getKey()) - keyworkCountMap.get(o1.getKey())))
                    );
                    mapList = mapList.subList(0, 5);
                    return mapList;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GoogleUrl> getGoogleUrlList(String keyword) throws Exception {
        String baseUrl ="http://www.google.com/search?q="+keyword+"&lr=lang_ko";
        Document baseDocument = connect(baseUrl).get();
        log.info("Url : " + baseUrl);
        Elements es = baseDocument.select("div.MjjYud");

        GoogleUrl sl;
        List<GoogleUrl> list = new ArrayList<>();
        for(Element e : es){
            String url = e.child(0).select("div.yuRUbf > a").attr("href");
            String title = e.child(0).select("div.yuRUbf > a > h3").text();
            String content = e.child(0).select("span").text();
            if (url.equals("") || title.equals("") || content.equals(""))
                continue;
            sl = new GoogleUrl(url, title, content);
            list.add(sl);
        }
        return list;
    }
}