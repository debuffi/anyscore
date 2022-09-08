package tv.cybergames.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tv.cybergames.domain.entity.Country;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private Long id;
    private String nickName;
    private String firstName;
    private String lastName;
    private Integer age;
    private Country country;
}
