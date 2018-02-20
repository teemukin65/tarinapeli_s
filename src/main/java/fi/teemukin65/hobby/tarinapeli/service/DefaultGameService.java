package fi.teemukin65.hobby.tarinapeli.service;

import fi.teemukin65.hobby.tarinapeli.dao.GameRepository;
import fi.teemukin65.hobby.tarinapeli.dao.PlayerRepository;
import fi.teemukin65.hobby.tarinapeli.dao.PlayersGameRepository;
import fi.teemukin65.hobby.tarinapeli.domain.Game;
import fi.teemukin65.hobby.tarinapeli.domain.Player;
import fi.teemukin65.hobby.tarinapeli.domain.PlayersGame;
import fi.teemukin65.hobby.tarinapeli.domain.PlayersGamePk;
import fi.teemukin65.hobby.tarinapeli.rest.dto.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultGameService implements GameService {
    private final Logger LOGGER = LoggerFactory.getLogger(DefaultGameService.class);

    private GameRepository gameRepository;

    private PlayerRepository playerRepository;

    private ModelMapper modelMapper;

    private PlayersGameRepository playersGameRepository;

    DefaultGameService(
            GameRepository gameRepository,
            PlayerRepository playerRepository,
            PlayersGameRepository playersGameRepository,
            ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.playersGameRepository = playersGameRepository;
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

        Player initiatingPlayer = playerRepository.findByEmail(initiator.getName().trim())
                .orElseThrow(() -> new UsernameNotFoundException("Game initiator not found"));

        newGame.setGameInitiator(initiatingPlayer.getId());
        ArrayList<Player> players = new ArrayList<>();
        players.add(initiatingPlayer);

        Game gameEntity = gameRepository.save(newGame);


        PlayersGamePk initiatingPlayersGameId = new PlayersGamePk(
                gameEntity.getGameId(), initiatingPlayer.getId(), PlayersGame.INITIATING_PLAYER_ORDER_NO);
        PlayersGame playersGame = new PlayersGame();
        playersGame.setPlayersGamePk(initiatingPlayersGameId);
        playersGame.setPlayerStatus("WAITING");

        playersGameRepository.save(playersGame);

        if (gameInfo.getPlayers() != null && !gameInfo.getPlayers().isEmpty()) {
            LOGGER.info("players {} included in creation message  ", gameInfo.getPlayers());
            throw new NotImplementedException();
        }
        return modelMapper.map(gameEntity, GameDto.class);
    }

    @Override
    public GameDto getGame(String gameId) {
        return null;
    }

    @Override
    public List<GamePlayerDto> addPlayer(String gameId, ValidList<GamePlayerAddDto> gamePlayerAddDtos) {
        LOGGER.debug("called with gameid: {}, with players:{}", gameId, gamePlayerAddDtos
        );
        Game game = gameRepository.findOne(Integer.valueOf(gameId.trim()));

        for (GamePlayerAddDto playerToAdd : gamePlayerAddDtos) {
            Optional<Player> optionalPlayer = playerRepository.findByEmail(playerToAdd.getEmail());
            if (!optionalPlayer.isPresent()) {

                // TODO: add new player with invited -status + add to optionalPlayer

            }

            // TODO: create PlayersGame entry


        }
        return null;
    }

    @Override
    public List<GamePlayerDto> getGamePlayers(String gameId) {
        return null;
    }
}
