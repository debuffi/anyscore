package gg.anyscore.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Vyacheslav Savinov
 * @since 10.09.2022
 */
@Data
@Entity
@Table(name = "achievement")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Player player;
    @OneToOne
    private Team team;
    @OneToOne
    private Tournament tournament;
    private Boolean isMajor;
    private Boolean isLAN;
    private String type; //top20, MVP, trophie tournament, place tournament
}
