package gg.anystats.domain.mapper;

import org.mapstruct.Mapper;

import gg.anystats.domain.dto.MatchDto;
import gg.anystats.domain.entity.Match;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
@Mapper
public interface MatchMapper {

    Match toEntity(MatchDto dto);

    MatchDto toDto(Match entity);
}
