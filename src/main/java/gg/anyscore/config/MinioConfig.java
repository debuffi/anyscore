package gg.anyscore.config;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gg.anyscore.config.properties.MinioProperties;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;

/**
 * @author Vyacheslav Savinov
 * @since 09.09.2022
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(value = MinioProperties.class)
public class MinioConfig {

    private final MinioProperties properties;

    @Bean
    public MinioClient generateMinioClient() {
        try {
            final OkHttpClient httpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.MINUTES)
                    .build();

            return MinioClient.builder()
                    .endpoint(properties.getUrl())
                    .httpClient(httpClient)
                    .credentials(properties.getAccessKey(), properties.getSecretValue())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
