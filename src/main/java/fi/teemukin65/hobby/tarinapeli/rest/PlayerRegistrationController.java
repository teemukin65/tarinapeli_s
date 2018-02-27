package fi.teemukin65.hobby.tarinapeli.rest;

import fi.teemukin65.hobby.tarinapeli.EmailExistException;
import fi.teemukin65.hobby.tarinapeli.rest.dto.PlayerDto;
import fi.teemukin65.hobby.tarinapeli.rest.dto.PlayerRegistrationDto;
import fi.teemukin65.hobby.tarinapeli.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static fi.teemukin65.hobby.tarinapeli.config.GamePathConstants.SIGN_UP_URL;

@Controller
public class PlayerRegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(PlayerRegistrationController.class);

    @Autowired
    PlayerService playerService;

    @RequestMapping(
            path = SIGN_UP_URL,
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    @ResponseBody
    public PlayerDto savePlayer(@RequestBody PlayerRegistrationDto registrationDto) throws EmailExistException {
        LOGGER.info("registerPlayer, called with Dto: {}", registrationDto);
        return playerService.registerPlayer(registrationDto);
    }


}
