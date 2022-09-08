package tv.cybergames.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import tv.cybergames.config.properties.MinioProperties;

/**
 * @author Vyacheslav Savinov
 * @since 09.09.2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    public List<Bucket> getAllBuckets() {
        try {
            return minioClient.listBuckets();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
