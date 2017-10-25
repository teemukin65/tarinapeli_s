package fi.teemukin65.hobby.tarinapeli.dao;

import fi.teemukin65.hobby.tarinapeli.domain.PlayersGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@RequestMapping(path = "/api/")
public interface PlayersGameRepository extends CrudRepository<PlayersGame, Integer> {
}
