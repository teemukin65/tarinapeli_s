package fi.teemukin65.hobby.tarinapeli.rest.dto;

import fi.teemukin65.hobby.tarinapeli.domain.GameStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class GameDto implements Serializable {
    private Integer gameId;

    private Timestamp gameInitiationTime;

    private Integer gameInitiator;

    private GameStatus gameStatus;

    private String gameTitle;

    private String gameDescription;

    private Timestamp gameStartTime;

    private Timestamp gameEndTime;

}
