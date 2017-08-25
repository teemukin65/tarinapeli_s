package fi.teemukin65.hobby.tarinapeli.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, precision = 32)
    private Integer id;

    @Column(name = "email", nullable = false, length = 250)
    @NotNull
    @Size(max = 250)
    private String email;

    @Column(name = "nickname", length = 20)
    @Size(max = 20)
    private String nickname;

    @Column(name = "active")
    private Boolean active;

}

