package fi.teemukin65.hobby.tarinapeli.service;

import fi.teemukin65.hobby.tarinapeli.rest.dto.*;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface GameService {

    List<GameDto> listUserGames(Principal user);

    GameDto addGame(GameAddDto newGame, Principal initiator);

    GameDto getGame(String gameId);

    List<GamePlayerDto> addPlayer(String gameId, ValidList<GamePlayerAddDto> gamePlayerAddDtos);

    List<GamePlayerDto> getGamePlayers(String gameId);
}
