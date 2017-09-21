package fi.teemukin65.hobby.tarinapeli.domain;

import fi.teemukin65.hobby.tarinapeli.query.tables.interfaces.IPlayersGame;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "players_game", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"game", "player", "order_no"})
})
public class PlayersGame implements IPlayersGame, Serializable {

    @Id
    private PlayersGamePk playersGamePk;

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

