package tv.cybergames.service.csgo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tv.cybergames.service.GameInfo;
import tv.cybergames.service.csgo.client.HLTVClient;
import tv.cybergames.service.csgo.parser.HLTVParser;

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
    public Object getMatches() {
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
    public Object getPlayers() {
        try {
            final ResponseEntity<String> response = hltvClient.getPlayersAsHTML();
            return hltvParser.parsePlayers(response.getBody());
        } catch (Exception e) {
            final String errorMessage = "HLTV GetPlayers -> error, message: %s".formatted(e.getMessage());
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
