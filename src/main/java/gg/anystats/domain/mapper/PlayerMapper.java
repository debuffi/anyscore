package gg.anystats.domain.mapper;

import org.mapstruct.Mapper;

import gg.anystats.domain.dto.PlayerDto;
import gg.anystats.domain.entity.Player;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
@Mapper
public interface PlayerMapper {

    Player toEntity(PlayerDto dto);

    PlayerDto toDto(Player entity);
}
