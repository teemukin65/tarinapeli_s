package fi.teemukin65.hobby.tarinapeli.rest.dto;

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
    private Long gameId;

    @NonNull
    @NotNull
    private Long playerId;


    private String gamePlayerStatus;

    @Length(max = 20)
    private String nickName;


}
