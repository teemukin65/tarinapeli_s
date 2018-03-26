package fi.teemukin65.hobby.tarinapeli.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import fi.teemukin65.hobby.tarinapeli.query.tables.interfaces.IPlayersGame;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "players_game", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"game", "player", "order_no"})
})
public class PlayersGame implements IPlayersGame, Serializable {

    public static final int INITIATING_PLAYER_ORDER_NO = 1;

    @Id
    @EmbeddedId
    @JsonUnwrapped
    private PlayersGamePk playersGamePk = new PlayersGamePk();

    @Column(name = "player_status", length = 10)
    @Size(max = 10)
    private String playerStatus;


    @Column(name = "game", nullable = false, precision = 32)
    @NotNull
    @ManyToOne(
            targetEntity = Game.class,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(name = "gameId")
    public Integer getGame() {
        return getPlayersGamePk().getGame();
    }

    public void setGame(Integer value) {
        getPlayersGamePk().setGame(value);
    }


    @Column(name = "player", nullable = false, precision = 32)
    @NotNull
    @ManyToOne(
            targetEntity = Player.class,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    public Integer getPlayer() {
        return getPlayersGamePk().getPlayer();
    }

    public void setPlayer(Integer value) {
        getPlayersGamePk().setPlayer(value);

    }


    @Column(name = "order_no", nullable = false, precision = 32)
    @NotNull
    @NonNull
    public Integer getOrderNo() {
        return getPlayersGamePk().getOrderNo();
    }

    @Override
    public void setOrderNo(Integer value) {
        getPlayersGamePk().setOrderNo(value);

    }


    @Override
    public void from(IPlayersGame from) {
        setGame(from.getGame());
        setPlayer(from.getPlayer());
        setOrderNo(from.getOrderNo());
        setPlayerStatus(from.getPlayerStatus());
    }

    @Override
    public <E extends IPlayersGame> E into(E into) {
        into.from(this);
        return into;
    }
}

