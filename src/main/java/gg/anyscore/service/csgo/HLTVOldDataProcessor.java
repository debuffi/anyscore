package gg.anyscore.service.csgo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import gg.anyscore.domain.dto.PlayerDto;
import gg.anyscore.domain.dto.StatsPlayerDto;
import gg.anyscore.domain.entity.Player;
import gg.anyscore.domain.mapper.PlayerMapper;
import gg.anyscore.repository.PlayerRepository;
import gg.anyscore.service.GameInfo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author Vyacheslav Savinov
 * @since 08.09.2022
 */
@Component
@RequiredArgsConstructor
public class HLTVOldDataProcessor {

    private final PlayerMapper playerMapper;
    private final GameInfo hltvBasedGameInfo;
    private final PlayerRepository playerRepository;

    @SneakyThrows
    public Iterable<Player> loadPlayers() {
        final List<StatsPlayerDto> statsPlayers = hltvBasedGameInfo.getStatsPlayers();
        final List<Player> players = new ArrayList<>();
        int i = 0;
        for (StatsPlayerDto statsPlayerDto : statsPlayers) {
            final Long id = statsPlayerDto.getId();
            final String name = statsPlayerDto.getName();
            final PlayerDto player = hltvBasedGameInfo.getPlayer(id, name);
            Thread.sleep(new Random().nextInt(200,  500));
            players.add(playerMapper.toEntity(player));
            System.out.println(i);
            i++;
        }

        return playerRepository.saveAll(players);
    }
}
