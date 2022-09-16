package gg.anyscore.service.csgo.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import gg.anyscore.domain.dto.MatchDto;
import gg.anyscore.domain.dto.PlayerDto;
import gg.anyscore.domain.dto.StatsPlayerDto;

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
        final Element player = Jsoup.parse(html).getElementsByClass("playerContainer").get(0);
        final String photoPath = player.getElementsByClass("playerBodyshot").get(0).select("img").get(1).attr("src");
        final String nickName = player.getElementsByClass("playerName").get(0).select("h1").text();
        final String playerRealname = player.getElementsByClass("playerRealname").get(0).text();
        final String countryName = player.getElementsByClass("playerRealname").get(0).select("img").attr("title");
        final String countryLogoPath = player.getElementsByClass("playerRealname").get(0).select("img").attr("src");
        String age = player.getElementsByClass("listRight").get(0).text();
        final Element team = player.getElementsByClass("listRight").get(1);
        final String teamName = team.select("a").get(0).text();
        String teamLogoPath = team.select("img").get(0).attr("src");
        if (!teamLogoPath.contains("http")) {
            teamLogoPath = "https://hltv.org" + teamLogoPath;
        }
        final String teamPath = "https://hltv.org" + team.select("a").get(0).attr("href");
        age = age.substring(0, age.indexOf(" "));

        final PlayerDto playerDto = new PlayerDto();
        playerDto.setAge(Integer.valueOf(age));
        playerDto.setFirstName(playerRealname.substring(0, playerRealname.indexOf(" ")));
        playerDto.setLastName(playerRealname.substring(playerRealname.indexOf(" ") + 1));
        playerDto.setNickName(nickName);
        playerDto.setCountryName(countryName);
        playerDto.setCountryLogoPath("https://hltv.org" + countryLogoPath);
        playerDto.setPhotoPath(photoPath);
        playerDto.setTeamName(teamName);
        playerDto.setTeamPath(teamPath);
        playerDto.setTeamLogoPath(teamLogoPath);

        final Elements media = player.getElementsByClass("socialMediaButtons").get(0).select("a");
        for (Element element : media) {
            final String href = element.attr("href");
            if (href.contains("twitter")) {
                playerDto.setTwitterUrl(href);
            } else if (href.contains("instagram")) {
                playerDto.setInstagramUrl(href);
            } else if (href.contains("twitch")) {
                playerDto.setTwitchUrl(href);
            }
        }
        return playerDto;
    }
}
