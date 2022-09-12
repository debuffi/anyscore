package gg.anystats.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gg.anystats.config.properties.ElasticSearchClientProperties;
import lombok.RequiredArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(value = ElasticSearchClientProperties.class)
public class ElasticSearchClientConfig {

    private final ElasticSearchClientProperties properties;

    @Bean
    public RestHighLevelClient elasticHighLevelClient() {
        final HttpHost[] hosts = new HttpHost[properties.getHosts().size()];
        for (int i = 0; i < properties.getHosts().size(); i++) {
            final String host = properties.getHosts().get(i);
            final Integer port = properties.getPorts().get(i);
            final HttpHost httpHost = new HttpHost(host, port, properties.getScheme());
            hosts[i] = httpHost;
        }
        return new RestHighLevelClient(RestClient.builder(hosts));
    }
}
