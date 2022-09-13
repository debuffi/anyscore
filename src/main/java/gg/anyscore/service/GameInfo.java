package gg.anyscore.service;

import java.util.List;

import gg.anyscore.domain.dto.MatchDto;
import gg.anyscore.domain.dto.PlayerDto;
import gg.anyscore.domain.dto.StatsPlayerDto;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
public interface GameInfo {

    List<MatchDto> getMatches();

    List<StatsPlayerDto> getStatsPlayers();

    PlayerDto getPlayer(Long playerId, String playerName);
}
