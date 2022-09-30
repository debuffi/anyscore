package gg.anyscore.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gg.anyscore.domain.entity.Picture;
import gg.anyscore.domain.model.PictureType;

/**
 * @author Vyacheslav Savinov
 * @since 13.09.2022
 */
@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {

    Optional<Picture> findByTitleAndType(String title, PictureType type);
}
