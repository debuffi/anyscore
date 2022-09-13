package gg.anyscore.domain.mapper;

import org.mapstruct.Mapper;

import gg.anyscore.domain.dto.MatchDto;
import gg.anyscore.domain.entity.Match;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
@Mapper
public interface MatchMapper {

    Match toEntity(MatchDto dto);

    MatchDto toDto(Match entity);
}
