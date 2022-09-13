package gg.anyscore.service.csgo;

import java.util.List;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gg.anyscore.domain.dto.MatchDto;
import gg.anyscore.domain.dto.PlayerDto;
import gg.anyscore.domain.dto.StatsPlayerDto;
import gg.anyscore.service.GameInfo;
import gg.anyscore.service.csgo.client.HLTVClient;
import gg.anyscore.service.csgo.parser.HLTVParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
            final String userAgent = new Random().nextInt(1000) + playerName + new Random().nextInt(1000);
            final ResponseEntity<String> response = hltvClient.getPlayerAsHtml(playerId, playerName, userAgent);
            return hltvParser.parsePlayer(response.getBody());
        } catch (Exception e) {
            final String errorMessage = "HLTV getPlayer -> error, message: %s".formatted(e.getMessage());
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
