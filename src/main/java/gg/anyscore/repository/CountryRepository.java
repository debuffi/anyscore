package gg.anyscore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gg.anyscore.domain.entity.Country;

/**
 * @author Vyacheslav Savinov
 * @since 13.09.2022
 */
@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
}