package example.springmovie.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(){

        RestClientBuilder builder = RestClient.builder(new HttpHost(
                "192.168.231.110",
                9200));
        return new RestHighLevelClient(builder);
    }

}