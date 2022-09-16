package gg.anyscore.service.csgo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import gg.anyscore.domain.dto.PlayerDto;
import gg.anyscore.domain.dto.StatsPlayerDto;
import gg.anyscore.domain.dto.file.FileUploadResponse;
import gg.anyscore.domain.entity.Country;
import gg.anyscore.domain.entity.Picture;
import gg.anyscore.domain.entity.Player;
import gg.anyscore.domain.entity.SocialAccount;
import gg.anyscore.domain.entity.Team;
import gg.anyscore.domain.mapper.PlayerMapper;
import gg.anyscore.domain.model.PictureType;
import gg.anyscore.repository.PictureRepository;
import gg.anyscore.repository.PlayerRepository;
import gg.anyscore.service.GameInfo;
import gg.anyscore.service.MinioService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Vyacheslav Savinov
 * @since 08.09.2022
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class HLTVOldDataProcessor {

    private final MinioService minioService;
    private final RestTemplate restTemplate;
    private final PlayerMapper playerMapper;
    private final GameInfo hltvBasedGameInfo;
    private final PlayerRepository playerRepository;
    private final PictureRepository pictureRepository;

    @SneakyThrows
    public Iterable<Player> loadPlayers() {
        final List<StatsPlayerDto> statsPlayers = hltvBasedGameInfo.getStatsPlayers()
                .stream().limit(20).toList();
        final List<Player> players = new ArrayList<>();
        int i = 0;
        for (StatsPlayerDto statsPlayerDto : statsPlayers) {
            final Long id = statsPlayerDto.getId();
            final String name = statsPlayerDto.getName();
            final PlayerDto player = hltvBasedGameInfo.getPlayer(id, name);
            final Picture countryPicture = loadPictureIfNeeded(player.getCountryLogoPath(), player.getCountryName(), PictureType.COUNTRY);
            final Picture photo = loadPictureIfNeeded(player.getPhotoPath(), player.getNickName(), PictureType.PLAYER);
            final Picture teamPicture = loadPictureIfNeeded(player.getTeamLogoPath(), player.getTeamName(), PictureType.TEAM);
            final Player entity = playerMapper.toEntity(player);
            entity.setCountry(new Country(null, player.getCountryName(), countryPicture));
            entity.setPhoto(photo);
            entity.setCurrentTeam(new Team(player.getTeamName(), teamPicture));
            entity.setSocialAccount(new SocialAccount(player.getTwitterUrl(), player.getInstagramUrl(), player.getTwitchUrl()));
            players.add(entity);

            Thread.sleep(new Random().nextInt(200, 500));
            System.out.println(i);
            i++;
        }

        return playerRepository.saveAll(players);
    }


    public Picture loadPictureIfNeeded(String url, String name, PictureType type) throws IOException {
        try {
            final String title = name.toLowerCase();
            final Optional<Picture> oPicture = pictureRepository.findByTitleAndType(title, type);
            if (oPicture.isPresent()) return oPicture.get();

            byte[] imageBytes = restTemplate.getForObject(url, byte[].class);
            final File file = new File(Files.write(Paths.get(title + ".jpg"), imageBytes).toUri());
            final FileUploadResponse response = minioService.uploadFile(type.name().toLowerCase(), file);

            final Picture picture = new Picture(response.getFolderPath(), response.getFileName(), title, type);
            return pictureRepository.save(picture);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
