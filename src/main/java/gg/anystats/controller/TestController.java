package gg.anystats.controller;

import java.io.File;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gg.anystats.domain.dto.PlayerDto;
import gg.anystats.service.GameInfo;
import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import gg.anystats.domain.dto.StatsPlayerDto;
import gg.anystats.domain.dto.file.FileUploadResponse;
import gg.anystats.service.MinioService;

/**
 * @author Vyacheslav Savinov
 * @since 08.09.2022
 */
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final GameInfo hltvBasedGameInfo;
    private final MinioService minioService;

    @GetMapping("/matches")
    public Object getMatches() {
        return hltvBasedGameInfo.getMatches();
    }


    @GetMapping("/stats/players")
    public List<StatsPlayerDto> getStatsPlayers() {
        return hltvBasedGameInfo.getStatsPlayers();
    }

    @GetMapping("/player")
    public PlayerDto getPlayer() {
        return hltvBasedGameInfo.getPlayer(11893L, "zywoo");
    }

    @GetMapping("/minio/buckets")
    public List<String> getAllBuckets() {
        return minioService.getAllBuckets().stream().map(Bucket::name).toList();
    }

    @GetMapping("/minio/delete")
    public void removeObject() {
        minioService.deleteFile("test", "123.png");
    }

    @GetMapping("/minio/create")
    public FileUploadResponse createOPbject() {
        return minioService.uploadFile("test", new File("123.png"));
    }

    @GetMapping("/minio/file")
    public void getFileByPath() {
         minioService.getFileByPath("test", "123.png");
    }

    @GetMapping("/minio/exists")
    public boolean objectExists() {
        return minioService.fileExists("test", "123.png");
    }
}
