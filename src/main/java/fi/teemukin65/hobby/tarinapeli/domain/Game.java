package fi.teemukin65.hobby.tarinapeli.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
@Entity
@Table(name = "game")
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id", unique = true, nullable = false, precision = 32)
    private Integer   gameId;

    @Column(name = "game_initiation_time", nullable = false)
    @NotNull
    private Timestamp gameInitiationTime;

//    @Column(name = "game_intiator", nullable = false, precision = 32)
    @ManyToOne(targetEntity = Player.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "game_intiator" )
    @NotNull
    private Integer   gameIntiator;

    @Column(name = "game_status")
    private String    gameStatus;

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

}
