package fi.teemukin65.hobby.tarinapeli.service;

import fi.teemukin65.hobby.tarinapeli.rest.dto.GameAddDto;
import fi.teemukin65.hobby.tarinapeli.rest.dto.GameDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class DefaultGameService implements GameService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public List<GameDto> listUserGames(Principal user) {
        LOGGER.debug("getting list of games for the user:{} [{}]", user.getName(), user.toString());

        return null;
    }

    @Override
    public GameDto addGame(GameAddDto newGame, Principal initiator) {
        return null;
    }

    @Override
    public GameDto getGame(String gameId) {
        return null;
    }
}
