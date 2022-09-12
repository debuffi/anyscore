package gg.anystats.service.csgo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gg.anystats.service.GameInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import gg.anystats.domain.dto.MatchDto;
import gg.anystats.domain.dto.PlayerDto;
import gg.anystats.domain.dto.StatsPlayerDto;
import gg.anystats.service.csgo.client.HLTVClient;
import gg.anystats.service.csgo.parser.HLTVParser;

;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HLTVBasedGameInfo implements GameInfo {

    private final HLTVClient hltvClient;
    private final HLTVParser hltvParser;

    @Override
    public List<MatchDto> getMatches() {
        try {
            final ResponseEntity<String> response = hltvClient.getMatchesAsHTML();
            return hltvParser.parseMatches(response.getBody());
        } catch (Exception e) {
            final String errorMessage = "HLTV GetMatches -> error, message: %s".formatted(e.getMessage());
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<StatsPlayerDto> getStatsPlayers() {
        try {
            final ResponseEntity<String> response = hltvClient.getStatsPlayersAsHTML();
            return hltvParser.parseStatsPlayers(response.getBody());
        } catch (Exception e) {
            final String errorMessage = "HLTV GetPlayers -> error, message: %s".formatted(e.getMessage());
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public PlayerDto getPlayer(Long playerId, String playerName) {
        try {
            final ResponseEntity<String> response = hltvClient.getPlayerAsHtml(playerId, playerName);
            return hltvParser.parsePlayer(response.getBody());
        } catch (Exception e) {
            final String errorMessage = "HLTV getPlayer -> error, message: %s".formatted(e.getMessage());
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
