package tv.cybergames.service.csgo.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import tv.cybergames.domain.dto.MatchDto;
import tv.cybergames.domain.dto.PlayerDto;
import tv.cybergames.domain.dto.StatsPlayerDto;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
@Component
public class HLTVParser {

    public List<MatchDto> parseMatches(final String html) {
        final Document document = Jsoup.parse(html);
        return List.of();
    }

    public List<StatsPlayerDto> parseStatsPlayers(final String html) {
        final Elements players = Jsoup.parse(html).select("tbody tr");
        List<StatsPlayerDto> result = new ArrayList<>();
        for (final Element player : players) {
            final Element playerCol = player.getElementsByClass("playerCol").get(0);
            final Element teamCol = player.getElementsByClass("teamCol").get(0);

            final Element playerColA = playerCol.select("a").get(0);
            final String playerName = playerColA.text();
            final String playerHref = playerColA.attr("href");
            final Long playerId = Long.valueOf(playerHref.substring(playerHref.indexOf("players/") + 8, playerHref.lastIndexOf("/")));

            final List<StatsPlayerDto.Team> teams = new ArrayList<>();
            final Elements teamsColA = teamCol.select("a");
            for (Element teamColA : teamsColA) {
                final String teamHref = teamColA.attr("href");
                final String teamName = teamColA.select("img").get(0).attr("title");
                final Long teamId = Long.valueOf(teamHref.substring(teamHref.indexOf("teams/") + 6, teamHref.lastIndexOf("/")));
                teams.add(new StatsPlayerDto.Team(teamId, teamName));
            }

            result.add(new StatsPlayerDto(playerId, playerName, teams));
        }
        return result;
    }

    public PlayerDto parsePlayer(final String html) {

        return null;
    }
}
