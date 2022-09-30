package gg.anyscore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gg.anyscore.domain.entity.Player;

/**
 * @author Vyacheslav Savinov
 * @since 12.09.2022
 */
@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

}
