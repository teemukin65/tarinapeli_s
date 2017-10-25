package fi.teemukin65.hobby.tarinapeli.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PlayerRegistrationDto {
    @NonNull
    @NotNull
    @Length(max = 250, min = 3)
    private String email;

    @NonNull
    @NotNull
    @Length(min = 5)
    private String password;

    @Length(max = 20)
    private String nickName;

}
