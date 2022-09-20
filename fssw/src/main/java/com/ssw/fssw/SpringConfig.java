package com.ssw.fssw;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.ssw.fssw.repository.board.BoardRepository;
import com.ssw.fssw.repository.board.JpaBoardRepository;
import com.ssw.fssw.repository.elasticsearch.ElasticSearchRepository;
import com.ssw.fssw.repository.elasticsearch.SearchRepository;
import com.ssw.fssw.repository.account.AccountRepository;
import com.ssw.fssw.repository.account.JpaAccountRepository;
import com.ssw.fssw.service.account.AccountService;
import com.ssw.fssw.service.search.SearchService;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final SearchRepository searchRepository;
    private ElasticsearchClient client;
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em/*, AccountRepository accountRepository*/) {
        // final 변수와 파라미터 주석까지 spring data jpa 사용할때 쓰는 것들
//        this.accountRepository = accountRepository;
        this.em = em;
        RestClient restClient = RestClient.builder(
                new HttpHost("49.50.161.128", 9200)).build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        this.client = new ElasticsearchClient(transport);
        this.searchRepository = new ElasticSearchRepository(client);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AccountService accountService(){
        return new AccountService(accountRepository());
    }
    @Bean
    public AccountRepository accountRepository(){
        return new JpaAccountRepository(em);
    }

    @Bean
    public BoardRepository boardRepository(){
        return new JpaBoardRepository(em);
    }

    @Bean
    public SearchService searchService(){
        return new SearchService(searchRepository);
    }
    
    // Aop에 직접 @Component를 이용해서 Component scan으로 사용해도 되고 이 방식으로 사용해도 됨
    // 이 방식으로 하면 한가지를 더 해줘야함
    // 이 프로젝트에선 Component scan 방식으로 진행
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }



//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

}
