package gg.anyscore.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 09.09.2022
 */
@Data
@Entity
@Table(name = "social_account")
@AllArgsConstructor
@NoArgsConstructor
public class SocialAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String twitter;
    private String instagram;
    private String twitch;

    public SocialAccount(final String twitter, final String instagram, final String twitch) {
        this.twitter = twitter;
        this.instagram = instagram;
        this.twitch = twitch;
    }
}
