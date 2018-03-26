package fi.teemukin65.hobby.tarinapeli.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class GamePlayerDto {


    @NonNull
    @NotNull
    @JsonProperty("game")
    private Integer game;

    @NonNull
    @NotNull
    @JsonProperty("player")
    private Integer player;

    @NonNull
    @NotNull
    private Integer orderNo;


    @JsonProperty("playerStatus")
    private String gamePlayerStatus;

    @Length(max = 20)
    private String nickName;


}
