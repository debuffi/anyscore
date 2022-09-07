package tv.cybergames.service.csgo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
@FeignClient(contextId = "HLTVClient", url = "http://hltv.org")
public interface HLTVClient {

    @GetMapping("/matches")
    ResponseEntity<String> getMatchesAsHTML();

    @GetMapping("/players")
    ResponseEntity<String> getPlayersAsHTML();
}
