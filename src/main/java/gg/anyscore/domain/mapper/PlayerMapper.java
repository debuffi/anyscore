package gg.anyscore.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import gg.anyscore.domain.dto.PlayerDto;
import gg.anyscore.domain.entity.Player;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
@Mapper
public interface PlayerMapper {

    @Mapping(source = "dto.twitterUrl", target = "socialAccount.twitter")
    @Mapping(source = "dto.instagramUrl", target = "socialAccount.instagram")
    @Mapping(source = "dto.twitchUrl", target = "socialAccount.twitch")
    Player toEntity(PlayerDto dto);

    PlayerDto toDto(Player entity);
}
