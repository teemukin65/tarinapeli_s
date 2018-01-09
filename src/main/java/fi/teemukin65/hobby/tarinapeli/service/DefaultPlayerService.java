package fi.teemukin65.hobby.tarinapeli.service;

import fi.teemukin65.hobby.tarinapeli.EmailExistException;
import fi.teemukin65.hobby.tarinapeli.dao.PlayerRepository;
import fi.teemukin65.hobby.tarinapeli.domain.Player;
import fi.teemukin65.hobby.tarinapeli.rest.dto.PlayerDto;
import fi.teemukin65.hobby.tarinapeli.rest.dto.PlayerRegistrationDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DefaultPlayerService implements PlayerService {
    private final Logger LOGGER = LoggerFactory.getLogger(DefaultPlayerService.class);

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @Override
    @Transactional
    public PlayerDto registerPlayer(PlayerRegistrationDto registrationDto) throws EmailExistException {
        LOGGER.debug("registerPlayer, given dto:{}", registrationDto);
        if (playerRepository.countPlayerByEmail(registrationDto.getEmail()) > 0) {
            throw new EmailExistException(
                    "Player already registered with that email address:" + registrationDto.getEmail()
            );
        }
        Player newPlayer = modelMapper.map(registrationDto, Player.class);

        newPlayer.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        LOGGER.debug("registerPlayer, new Player parameter:{}", newPlayer);
        return modelMapper.map(playerRepository.save(newPlayer), PlayerDto.class);

    }
}
