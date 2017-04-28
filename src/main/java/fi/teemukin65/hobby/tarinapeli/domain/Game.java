package fi.teemukin65.hobby.tarinapeli.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;

/**
 * Created by teemu on 24.4.2017.
 */
@Entity
@Data
public class Game {
    @Id
    @GeneratedValue
    private Long gameId; // for now
    @Column(nullable = false)
    private String gameTitle;

    @Column(nullable = false)
    private GameStatus gameStatus;
    // Will be reference to Player soon

    private String gameInitiator;
    private String gameDescription;
    @Column(nullable = false)
    private ZonedDateTime gameInitiationTime;
    private ZonedDateTime gameStartTime;
    private ZonedDateTime gameEndTime;

    protected Game(){
        this.gameTitle="";
        this.gameStatus=GameStatus.INITIATING;
        this.gameInitiationTime= ZonedDateTime.now();
    }

    public Game(String gameTitle, String gameDescription) {
        this();
        this.gameTitle = gameTitle;
        this.gameDescription = gameDescription;
    }
}
