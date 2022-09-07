package tv.cybergames.service.csgo.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
@Component
public class HLTVParser {

    public Object parseMatches(final String html) {
        final Document document = Jsoup.parse(html);
        return html;
    }

    public Object parsePlayers(final String html) {
        return html;
    }
}
