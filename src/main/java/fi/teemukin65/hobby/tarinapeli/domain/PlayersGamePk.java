package fi.teemukin65.hobby.tarinapeli.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class PlayersGamePk implements Serializable {

    private Integer game;
    private Integer player;
    @Column(name = "order_no", nullable = false, precision = 32)
    private Integer orderNo;

}
