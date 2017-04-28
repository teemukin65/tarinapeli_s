package fi.teemukin65.hobby.tarinapeli.repository;

import fi.teemukin65.hobby.tarinapeli.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by teemu on 28.4.2017.
 */
@RepositoryRestResource
public interface GameRepository extends CrudRepository<Game, Long> {

}
