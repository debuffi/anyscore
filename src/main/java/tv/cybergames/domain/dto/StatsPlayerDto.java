package tv.cybergames.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 08.09.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsPlayerDto {

    private Long id;
    private String name;
    private List<Team> teams;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Team {
        private Long id;
        private String name;
    }
}
