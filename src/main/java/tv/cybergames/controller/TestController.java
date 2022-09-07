package tv.cybergames.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tv.cybergames.service.GameInfo;

/**
 * @author Vyacheslav Savinov
 * @since 08.09.2022
 */
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final GameInfo hltvBasedGameInfo;

    @GetMapping("/matches")
    public Object getMatches(){
        return hltvBasedGameInfo.getMatches();
    }



    @GetMapping("/players")
    public Object getPlayers(){
        return hltvBasedGameInfo.getPlayers();
    }
}
