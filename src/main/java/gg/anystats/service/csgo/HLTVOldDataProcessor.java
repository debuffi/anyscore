package gg.anystats.service.csgo;

import java.util.List;

import org.springframework.stereotype.Component;

import gg.anystats.service.GameInfo;
import lombok.RequiredArgsConstructor;
import gg.anystats.domain.dto.StatsPlayerDto;

/**
 * @author Vyacheslav Savinov
 * @since 08.09.2022
 */
@Component
@RequiredArgsConstructor
public class HLTVOldDataProcessor {

    private final GameInfo hltvBasedGameInfo;

    public void processData() {
        final List<StatsPlayerDto> statsPlayers = hltvBasedGameInfo.getStatsPlayers();
    }
}
