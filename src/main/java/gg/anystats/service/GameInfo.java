package gg.anystats.service;

import java.util.List;

import gg.anystats.domain.dto.MatchDto;
import gg.anystats.domain.dto.PlayerDto;
import gg.anystats.domain.dto.StatsPlayerDto;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
public interface GameInfo {

    List<MatchDto> getMatches();

    List<StatsPlayerDto> getStatsPlayers();

    PlayerDto getPlayer(Long playerId, String playerName);
}
