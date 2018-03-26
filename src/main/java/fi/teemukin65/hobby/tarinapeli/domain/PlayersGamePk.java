package fi.teemukin65.hobby.tarinapeli.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data

@Embeddable
public class PlayersGamePk implements Serializable {

    private Integer game;
    private Integer player;
    @Column(name = "order_no", nullable = false, precision = 32)
    private Integer orderNo;

    @java.beans.ConstructorProperties({"game", "player", "orderNo"})
    public PlayersGamePk(Integer game, Integer player, Integer orderNo) {
        this.game = game;
        this.player = player;
        this.orderNo = orderNo;
    }

    public PlayersGamePk() {
    }
}
