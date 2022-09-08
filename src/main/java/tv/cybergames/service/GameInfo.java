package tv.cybergames.service;

import java.util.List;

import tv.cybergames.domain.dto.MatchDto;
import tv.cybergames.domain.dto.PlayerDto;
import tv.cybergames.domain.dto.StatsPlayerDto;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
public interface GameInfo {

    List<MatchDto> getMatches();

    List<StatsPlayerDto> getStatsPlayers();

    PlayerDto getPlayer(Long playerId, String playerName);
}
