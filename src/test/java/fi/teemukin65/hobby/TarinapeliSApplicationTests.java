package fi.teemukin65.hobby;

import fi.teemukin65.hobby.tarinapeli.TarinapeliSApplication;
import fi.teemukin65.hobby.tarinapeli.config.GamePathConstants;
import fi.teemukin65.hobby.tarinapeli.domain.GameStatus;
import fi.teemukin65.hobby.tarinapeli.rest.dto.*;
import org.jooq.impl.DefaultDSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static fi.teemukin65.hobby.tarinapeli.config.GamePathConstants.GAME_ROOT_URL;
import static fi.teemukin65.hobby.tarinapeli.config.GamePathConstants.PLAYER_URL;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {
                TarinapeliSApplication.class

        }
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class TarinapeliSApplicationTests {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DefaultDSLContext dslContext;

    @Value("${random.int}")
    Integer testPersonId;


	@Test
    public void contextLoads() {
        logger.debug("contextLoads");
    }


    @Test
    public void playerRegistrationOK() {
        PlayerRegistrationDto registrationDto = new PlayerRegistrationDto();
        String email = "test" + testPersonId.toString() + "@" + "example.com";
        registrationDto.setEmail(email);
        registrationDto.setPassword("secret");
        HttpEntity<PlayerRegistrationDto> request = new HttpEntity<>(registrationDto);

        PlayerDto body = this.restTemplate.postForObject(GamePathConstants.SIGN_UP_URL, request, PlayerDto.class);
        assertThat(body.getEmail()).isEqualTo(email);
        // Login
        String authorizationToken;
        PlayerLoginDto loginDto = new PlayerLoginDto(registrationDto.getEmail(), registrationDto.getPassword());
        HttpHeaders jsonContentType = new HttpHeaders();
        jsonContentType.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PlayerLoginDto> loginRequest = new HttpEntity<>(loginDto, jsonContentType);
        HttpEntity<Object> loggedIn = this.restTemplate.postForEntity(
                GamePathConstants.LOGIN_URL,
                loginRequest,
                Object.class);
        authorizationToken = loggedIn.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        HttpHeaders authorizatioHeader = new HttpHeaders();
        authorizatioHeader.add(HttpHeaders.AUTHORIZATION, authorizationToken);
        authorizatioHeader.setContentType(MediaType.APPLICATION_JSON);
        // new game
        GameAddDto newGameDto = new GameAddDto();
        String testTitle = "Uusi testi Peli";
        newGameDto.setGameTitle(testTitle);
        HttpEntity<GameAddDto> gameCreateRequest = new HttpEntity<>(newGameDto, authorizatioHeader);

        ResponseEntity<GameDto> createdGame = this.restTemplate.exchange(
                GAME_ROOT_URL,
                HttpMethod.POST,
                gameCreateRequest, GameDto.class);
        logger.debug("foundGame: {}", createdGame);
        assertThat(createdGame.getBody().getGameTitle()).isEqualTo(testTitle);
        assertThat(createdGame.getBody().getGameStatus()).isEqualTo(GameStatus.INITIATING);
        assertThat(createdGame.getStatusCode()).isEqualTo(HttpStatus.CREATED);


        HttpEntity<Object> gamePlayersGetRequest = new HttpEntity(authorizatioHeader);
        ResponseEntity<List<GamePlayerDto>> initialPlayers = this.restTemplate.exchange(
                GAME_ROOT_URL + "/" +
                        createdGame.getBody().getGameId().toString() + PLAYER_URL,
                HttpMethod.GET,
                gamePlayersGetRequest, new ParameterizedTypeReference<List<GamePlayerDto>>() {
                }
        );
        assertThat(initialPlayers.getBody().size()).isEqualTo(1);





    }

}
