package fi.teemukin65.hobby.tarinapeli.service;

import fi.teemukin65.hobby.tarinapeli.EmailExistException;
import fi.teemukin65.hobby.tarinapeli.rest.dto.PlayerDto;
import fi.teemukin65.hobby.tarinapeli.rest.dto.PlayerRegistrationDto;

public interface PlayerService {

    PlayerDto registerPlayer(PlayerRegistrationDto registrationDto) throws EmailExistException;
}
