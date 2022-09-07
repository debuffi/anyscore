package tv.cybergames.domain.mapper;

import org.mapstruct.Mapper;

import tv.cybergames.domain.dto.MatchDto;
import tv.cybergames.domain.dto.PlayerDto;
import tv.cybergames.domain.entity.Match;
import tv.cybergames.domain.entity.Player;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
@Mapper
public interface MatchMapper {

    Match toEntity(MatchDto dto);

    MatchDto toDto(Match entity);
}
