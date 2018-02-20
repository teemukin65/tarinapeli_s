package fi.teemukin65.hobby.tarinapeli.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class GamePlayerAddDto {
    @NonNull
    @NotNull
    @Length(max = 250, min = 3)
    private String email;


}


