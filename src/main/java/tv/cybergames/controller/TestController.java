package tv.cybergames.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import tv.cybergames.domain.dto.PlayerDto;
import tv.cybergames.domain.dto.StatsPlayerDto;
import tv.cybergames.service.GameInfo;
import tv.cybergames.service.MinioService;

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


    @GetMapping("/ststs/players")
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
}
