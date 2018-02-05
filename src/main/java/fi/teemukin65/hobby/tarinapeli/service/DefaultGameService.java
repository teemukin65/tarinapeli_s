package fi.teemukin65.hobby.tarinapeli.service;

import fi.teemukin65.hobby.tarinapeli.dao.GameRepository;
import fi.teemukin65.hobby.tarinapeli.dao.PlayerRepository;
import fi.teemukin65.hobby.tarinapeli.domain.Game;
import fi.teemukin65.hobby.tarinapeli.domain.Player;
import fi.teemukin65.hobby.tarinapeli.rest.dto.GameAddDto;
import fi.teemukin65.hobby.tarinapeli.rest.dto.GameDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultGameService implements GameService {
    private final Logger LOGGER = LoggerFactory.getLogger(DefaultGameService.class);

    private GameRepository gameRepository;

    private PlayerRepository playerRepository;

    private ModelMapper modelMapper;

    DefaultGameService(
            GameRepository gameRepository,
            PlayerRepository playerRepository,
            ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GameDto> listUserGames(Principal user) {
        LOGGER.debug("getting list of games for the user:{} [{}]", user.getName(), user.toString());

        // TODO: restrict games for games where logged in user is in
        ArrayList<GameDto> gameDtos = new ArrayList<>();
        Iterable<Game> games = gameRepository.findAll();
        LOGGER.info("Found {} Games");
        games.forEach((game) -> {
            LOGGER.debug("listUserGames..found game:{}", game);
            gameDtos.add(modelMapper.map(game, GameDto.class));
        });
        return gameDtos;
    }

    @Override
    @Transactional
    public GameDto addGame(@Valid GameAddDto gameInfo, Principal initiator) {
        Game newGame = new Game();
        newGame.setGameInitiationTime(new Timestamp(System.currentTimeMillis()));
        newGame.setGameTitle(gameInfo.getGameTitle());
        if (gameInfo.getGameDescription() != null) {
            newGame.setGameDescription(gameInfo.getGameDescription().trim());
        }

        Player initiatingPlayer = playerRepository.findByEmail(initiator.getName().trim());
        newGame.setGameInitiator(initiatingPlayer.getId());
        ArrayList<Player> players = new ArrayList<>();
        players.add(initiatingPlayer);


        if (gameInfo.getPlayers() != null && !gameInfo.getPlayers().isEmpty()) {
            LOGGER.info("players {} included in creation message  ", gameInfo.getPlayers());
            throw new NotImplementedException();
        }
        return modelMapper.map(gameRepository.save(newGame), GameDto.class);
    }

    @Override
    public GameDto getGame(String gameId) {
        return null;
    }
}
