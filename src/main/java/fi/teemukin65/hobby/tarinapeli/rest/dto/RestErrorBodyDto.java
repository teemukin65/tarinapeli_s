package fi.teemukin65.hobby.tarinapeli.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class RestErrorBodyDto {
    private Integer code;
    private String message;
    private String description;
    private List<FieldError> errors;

    private class FieldError {
        private Integer code;
        private String field;
        private String message;
    }

}
