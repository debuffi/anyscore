package gg.anystats.service.csgo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
@FeignClient(url = "https://hltv.org", name = "HLTVClient")
public interface HLTVClient {

    @GetMapping("/matches")
    ResponseEntity<String> getMatchesAsHTML();

    @GetMapping("/stats/players")
    ResponseEntity<String> getStatsPlayersAsHTML();

    @GetMapping("/player/{playerId}/{playerName}")
    ResponseEntity<String> getPlayerAsHtml(@PathVariable Long playerId, @PathVariable String playerName);
}
