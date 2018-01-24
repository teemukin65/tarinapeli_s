package fi.teemukin65.hobby.tarinapeli.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class PlayerLoginDto {
    @NotNull
    @NonNull
    @Size(max = 250)
    private String email;

    @Size(max = 60)
    @NotNull
    @NonNull
    private String password;
}