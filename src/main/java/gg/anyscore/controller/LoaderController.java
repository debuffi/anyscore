package gg.anyscore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gg.anyscore.domain.entity.Player;
import gg.anyscore.service.csgo.HLTVOldDataProcessor;
import lombok.RequiredArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 12.09.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loader")
public class LoaderController {

    private final HLTVOldDataProcessor dataProcessor;


    @GetMapping("/players")
    public Iterable<Player> loadPlayers(){
        return dataProcessor.loadPlayers();
    }
}
