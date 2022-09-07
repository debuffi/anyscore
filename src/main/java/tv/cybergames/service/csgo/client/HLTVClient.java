package tv.cybergames.service.csgo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
@FeignClient(url = "https://hltv.org", name = "HLTVClient")
public interface HLTVClient {

    @GetMapping("/matches")
    ResponseEntity<String> getMatchesAsHTML();

    @GetMapping("/players")
    ResponseEntity<String> getPlayersAsHTML();
}
