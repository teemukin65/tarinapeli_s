package fi.teemukin65.hobby.tarinapeli.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class GameAddDto {

    @NonNull
    @NotNull
    @Length(max = 80, min = 3)
    private String gameTitle;

    @Length(max = 160)
    private String gameDescription;

    @Email
    private List<String> players;

}
