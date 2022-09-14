package gg.anyscore.config.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author Vyacheslav Savinov
 * @since 09.09.2022
 */
@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {
    private String url;
    private String accessKey;
    private String secretValue;
    private List<String> bucketNames;
}
