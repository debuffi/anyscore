package tv.cybergames.service.csgo;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tv.cybergames.domain.dto.StatsPlayerDto;
import tv.cybergames.service.GameInfo;

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
