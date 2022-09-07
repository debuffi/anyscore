package tv.cybergames.config.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
@Data
@ConfigurationProperties(prefix = "elastic-search")
public class ElasticSearchClientProperties {

    private String scheme;
    private List<String> hosts;
    private List<Integer> ports;
}
