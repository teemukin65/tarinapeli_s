package fi.teemukin65.hobby.tarinapeli.rest.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class RestErrorBodyDto {
    private Integer code;
    private String message;
    private String description;
    private List<FieldError> errors;

    @Data
    public static class FieldError {
        final private Integer code;
        @NonNull
        final private String field;
        final private String message;
    }

}
