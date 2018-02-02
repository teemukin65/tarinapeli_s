package fi.teemukin65.hobby.tarinapeli.rest;

import fi.teemukin65.hobby.tarinapeli.rest.dto.GameAddDto;
import fi.teemukin65.hobby.tarinapeli.rest.dto.GameDto;
import fi.teemukin65.hobby.tarinapeli.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static fi.teemukin65.hobby.tarinapeli.config.GamePathConstants.GAME_ROOT_URL;

@RestController
@RequestMapping(
        path = GAME_ROOT_URL,
        produces = "application/json; charset=UTF-8",
        consumes = "application/json; charset=UTF-8"
)
public class GameController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    GameService gameService;

    @RequestMapping(method = RequestMethod.GET)
    List<GameDto> listGames(Principal user) {

        LOGGER.debug("listGames, user:{}", user.getName());
        return gameService.listUserGames(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    GameDto addGame(@RequestBody @Valid GameAddDto gameAddDto, Principal initiator) {
        LOGGER.debug("adding Game, got:{}, for initiator: {}", gameAddDto, initiator.getName());
        return gameService.addGame(gameAddDto, initiator);
    }

    @GetMapping(value = "/{gameId}")
    GameDto getGame(@PathVariable String gameId) {
        return gameService.getGame(gameId);
    }
}
