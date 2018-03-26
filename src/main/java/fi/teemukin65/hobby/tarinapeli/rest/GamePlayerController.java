package fi.teemukin65.hobby.tarinapeli.rest;

import fi.teemukin65.hobby.tarinapeli.EmailExistException;
import fi.teemukin65.hobby.tarinapeli.rest.dto.GamePlayerAddDto;
import fi.teemukin65.hobby.tarinapeli.rest.dto.GamePlayerDto;
import fi.teemukin65.hobby.tarinapeli.rest.dto.ValidList;
import fi.teemukin65.hobby.tarinapeli.service.GameService;
import fi.teemukin65.hobby.tarinapeli.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static fi.teemukin65.hobby.tarinapeli.config.GamePathConstants.GAME_ROOT_URL;
import static fi.teemukin65.hobby.tarinapeli.config.GamePathConstants.PLAYER_URL;

@RestController
@RequestMapping(path = GAME_ROOT_URL + "/{gameId}" + PLAYER_URL)
public class GamePlayerController {
    private final Logger LOGGER = LoggerFactory.getLogger(GamePlayerController.class);

    @Autowired
    PlayerService playerService;

    @Autowired
    GameService gameService;

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    public List<GamePlayerDto> addPlayerToGame(
            @RequestBody ValidList<GamePlayerAddDto> gamePlayerAddDtos,
            @PathVariable(required = true, name = "gameId") String gameId) throws EmailExistException {
        LOGGER.info("addPlayerToGame, called with Dto: {}", gamePlayerAddDtos);

        return gameService.addPlayer(gameId, gamePlayerAddDtos);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            consumes = "application/json",
            produces = "application/json"
    )
    public List<GamePlayerDto> getGamePlayers(
            @PathVariable(required = true, name = "gameId") String gameId,
            Principal user) {
        LOGGER.info("getGamePlayers, called for game:{}", gameId);
        //TODO: gameId validation
        return gameService.getGamePlayers(gameId);
    }


}
