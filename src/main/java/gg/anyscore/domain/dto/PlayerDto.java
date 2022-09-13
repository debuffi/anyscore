package gg.anyscore.domain.dto;

import gg.anyscore.domain.entity.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import gg.anyscore.domain.entity.Country;

/**
 * @author Vyacheslav Savinov
 * @since 07.09.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private Long id;
    private String photoPath;
    private String nickName;
    private String firstName;
    private String lastName;
    private Integer age;
    private String countryName;
    private String countryLogoPath;
}
