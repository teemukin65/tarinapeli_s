package fi.teemukin65.hobby.tarinapeli.rest;

import fi.teemukin65.hobby.tarinapeli.EmailExistException;
import fi.teemukin65.hobby.tarinapeli.rest.dto.PlayerDto;
import fi.teemukin65.hobby.tarinapeli.rest.dto.PlayerRegistrationDto;
import fi.teemukin65.hobby.tarinapeli.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/game/player")
public class PlayerController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    PlayerService playerService;

    @RequestMapping(
            path = "/sign-up",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    public PlayerDto savePlayer(@RequestBody PlayerRegistrationDto registrationDto) throws EmailExistException {
        LOGGER.info("registerPlayer, called with Dto: {}", registrationDto);
        return playerService.registerPlayer(registrationDto);
    }


}
