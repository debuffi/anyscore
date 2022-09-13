package gg.anyscore.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import gg.anyscore.config.properties.MinioProperties;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListObjectsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.Result;
import io.minio.StatObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import gg.anyscore.domain.dto.file.FileUploadResponse;

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
        try {
            minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(filePath).build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Iterable<Result<Item>> getFilesByBucketName(String bucketName) {
        return minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
    }

    @SneakyThrows
    public void getFileByPath(String bucketName, String filePath) {
        final GetObjectResponse object = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(filePath).build());
        File outputFile = new File("test.png");
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(object.readAllBytes());
        }
        object.readAllBytes();
    }

    @SneakyThrows
    public String getPresignedUrl(String bucketName, String filePath) {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .bucket(bucketName)
                        .object(filePath)
                        .build()
        );
    }

    @SneakyThrows
    public void deleteFile(String bucketName, String filePath) {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(filePath).build());
    }

    @SneakyThrows
    public FileUploadResponse uploadFile(final String bucketName, final File file) {
        final String folderPath = UUID.randomUUID().toString();
        final String key = folderPath + "/" + file.getName();
        minioClient.uploadObject(UploadObjectArgs.builder().bucket(bucketName).object(file.getName()).filename(file.getName()).build());
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
