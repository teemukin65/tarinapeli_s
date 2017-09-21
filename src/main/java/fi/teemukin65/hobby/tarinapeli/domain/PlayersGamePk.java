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

}
