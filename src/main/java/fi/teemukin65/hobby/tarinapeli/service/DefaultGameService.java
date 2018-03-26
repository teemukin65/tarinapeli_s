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

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            throw new RuntimeException("NOT IMPLEMENTED!");
        }
        return modelMapper.map(gameEntity, GameDto.class);
    }

    @Override
    public GameDto getGame(String gameId) {
        return null;
    }

    @Override
    @Transactional
    public List<GamePlayerDto> addPlayer(String gameId, ValidList<GamePlayerAddDto> gamePlayerAddDtos) {
        LOGGER.debug("called with gameid: {}, with players:{}", gameId, gamePlayerAddDtos
        );
        Game game = gameRepository.findOne(Integer.valueOf(gameId.trim()));
        // TODO: Check if there are already players added to the Game
        List<PlayersGame> existingGamePlayers = playersGameRepository.findByPlayersGamePk_Game(game.getGameId());
        List<PlayersGame> newGamePlayers = new ArrayList<PlayersGame>();
        List<GamePlayerDto> gamePlayerDtosToReturn = new ArrayList<>();
        Integer orderNo = existingGamePlayers.size() + 1;

        for (GamePlayerAddDto playerToAdd : gamePlayerAddDtos) {
            String playerEmail = playerToAdd.getEmail();
            Player player = playerRepository.findByEmail(playerEmail)
                    .orElseGet(() -> {
                        Player passivePlayer = new Player(playerEmail);
                        passivePlayer.setActive(false);

                        return playerRepository.save(passivePlayer);
                    });

            PlayersGamePk playersGamePk = new PlayersGamePk(game.getGameId(), player.getId(), orderNo);
            PlayersGame playersGameToAdd = new PlayersGame();
            playersGameToAdd.setPlayersGamePk(playersGamePk);
            // TODO: Java enum type for gameplayer statuses
            playersGameToAdd.setPlayerStatus("ADDED");

            orderNo = orderNo + 1;
        }
        return newGamePlayers.stream()
                .map(entity -> modelMapper.map(entity, GamePlayerDto.class))
                .collect(Collectors.toList());


    }

    ;

    @Override
    public List<GamePlayerDto> getGamePlayers(String gameId) {
        List<PlayersGame> playersGames = playersGameRepository.findByPlayersGamePk_Game(Integer.valueOf(gameId));
        LOGGER.debug("getGamePlayers, playersGame entities found:{}", playersGames);
        return playersGames.stream()
                .map(playersGame -> {

                    LOGGER.debug("mapping: {}", playersGame);
                    GamePlayerDto playersGameMapped = modelMapper.map(playersGame, GamePlayerDto.class);
                    LOGGER.debug("succeeded to:", playersGameMapped);
                    return playersGameMapped;
                })
                .collect(Collectors.toList());
    }
}
