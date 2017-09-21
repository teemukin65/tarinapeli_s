package fi.teemukin65.hobby.tarinapeli.domain;

import fi.teemukin65.hobby.tarinapeli.query.tables.interfaces.IPlayer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "player")
public class Player implements IPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, precision = 32)
    private Integer id;

    @Column(name = "email", nullable = false, length = 250)
    @NotNull
    @NonNull
    @Size(max = 250)
    private String email;

    @Column(name = "nickname", length = 20)
    @Size(max = 20)
    private String nickname;

    @Column(name = "active")
    private Boolean active;

    @Override
    public void from(IPlayer from) {
        setId(from.getId());
        setEmail(from.getEmail());
        setNickname(from.getNickname());
        setActive(from.getActive());
    }

    @Override
    public <E extends IPlayer> E into(E into) {
        into.from(this);
        return into;
    }

}

