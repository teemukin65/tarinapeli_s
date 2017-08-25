package fi.teemukin65.hobby.tarinapeli.dao;

import fi.teemukin65.hobby.tarinapeli.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer>{
}
