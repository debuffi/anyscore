package tv.cybergames.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import io.minio.BucketExistsArgs;
import io.minio.ListObjectsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import tv.cybergames.config.properties.MinioProperties;
import tv.cybergames.domain.dto.file.FileUploadResponse;

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

    @PostConstruct
    public void onStart() {
        final String bucketName = minioProperties.getBucketName();
        final boolean bucketExists = bucketExists(bucketName);
        if (!bucketExists) {
            makeBucket(bucketName);
        }
    }

    @SneakyThrows
    private boolean bucketExists(String bucketName) {
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            log.error("bucketExists -> error, bucketName: {}", bucketName, e);
            throw e;
        }
    }

    @SneakyThrows
    private void makeBucket(String bucketName) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            log.error("makeBucket -> error, bucketName: {}", bucketName, e);
            throw e;
        }
    }

    public boolean fileExists(String bucketName, String filePath) {
        return minioClient.doesObjectExist(bucketName, filePath);
    }

    public Iterable<Result<Item>> getFilesByBucketName(String bucketName) {
        return minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
    }

    public void deleteFile(String bucketName, String filePath) {
        minioClient.deleteObject(bucketName, filePath);
    }

    public FileUploadResponse uploadFile(final String bucketName, final File file) {
        final String folderPath = UUID.randomUUID().toString();
        final String key = folderPath + "/" + file.getName();
        final PutObjectRequest request = new PutObjectRequest(bucketName, key, file);
        minioClient.putObject(request);
        return new FileUploadResponse(file.getName(), folderPath);
    }

    public List<Bucket> getAllBuckets() {
        try {
            return minioClient.listBuckets();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
