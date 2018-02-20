package fi.teemukin65.hobby.tarinapeli.dao;

import fi.teemukin65.hobby.tarinapeli.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Repository()
@RequestMapping(path = "/api/")
public interface PlayerRepository extends CrudRepository<Player, Integer>{
    Optional<Player> findByEmail(String email);
    int countPlayerByEmail(String email);

}
