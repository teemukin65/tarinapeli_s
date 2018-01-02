package fi.teemukin65.hobby.tarinapeli.rest;

import fi.teemukin65.hobby.tarinapeli.rest.dto.GameAddDto;
import fi.teemukin65.hobby.tarinapeli.rest.dto.GameDto;
import fi.teemukin65.hobby.tarinapeli.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/game")
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping
    List<GameDto> listGames(Principal user) {
        return gameService.listUserGames(user);
    }

    @PostMapping
    GameDto addGame(@RequestBody GameAddDto gameAddDto, Principal initiator) {
        return gameService.addGame(gameAddDto, initiator);
    }

    @GetMapping(value = "/{gameId}")
    GameDto getGame(@PathVariable String gameId) {
        return gameService.getGame(gameId);
    }
}
