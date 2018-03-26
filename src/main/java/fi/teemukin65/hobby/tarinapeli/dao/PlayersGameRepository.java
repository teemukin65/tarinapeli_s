package fi.teemukin65.hobby.tarinapeli.dao;

import fi.teemukin65.hobby.tarinapeli.domain.PlayersGame;
import fi.teemukin65.hobby.tarinapeli.domain.PlayersGamePk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
@RequestMapping(path = "/api/")
public interface PlayersGameRepository extends CrudRepository<PlayersGame, PlayersGamePk> {
    List<PlayersGame> findByPlayersGamePk_Game(Integer game);
}
