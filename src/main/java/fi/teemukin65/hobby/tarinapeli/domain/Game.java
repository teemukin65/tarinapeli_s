package fi.teemukin65.hobby.tarinapeli.domain;

import fi.teemukin65.hobby.tarinapeli.query.tables.interfaces.IGame;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
@Entity
@Table(name = "game")
@Data
@NoArgsConstructor
public class Game implements IGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id", unique = true, nullable = false, precision = 32)
    @NonNull
    private Integer gameId;

    @Column(name = "game_initiation_time",
            insertable = false)
    private Timestamp gameInitiationTime;

    @Column(name = "game_initiator", nullable = false, precision = 32)
    @NonNull
    private Integer gameInitiator;

    @Column(name = "game_status")
    private String gameStatus = GameStatus.INITIATING.toString();

    @Column(name = "game_title", length = 80)
    @Size(max = 80)
    private String    gameTitle;


    @Column(name = "game_description", length = 160)
    @Size(max = 160)
    private String    gameDescription;

    @Column(name = "game_start_time")
    private Timestamp gameStartTime;

    @Column(name = "game_end_time")
    private Timestamp gameEndTime;

    @Override
    public void from(IGame from) {
        setGameId(from.getGameId());
        setGameStatus(from.getGameStatus());
        setGameTitle(from.getGameTitle());
        setGameDescription(from.getGameDescription());
        setGameStartTime(from.getGameStartTime());
        setGameEndTime(from.getGameEndTime());
    }

    @Override
    public <E extends IGame> E into(E into) {
        into.from(this);
        return into;
    }
}

