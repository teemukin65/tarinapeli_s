package fi.teemukin65.hobby.tarinapeli.rest;

import fi.teemukin65.hobby.tarinapeli.EmailExistException;
import fi.teemukin65.hobby.tarinapeli.rest.dto.RestErrorBodyDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> restClientExceptionHandler(Throwable ex, WebRequest req) {
        RestErrorBodyDto bodyOfResponse = new RestErrorBodyDto();
        bodyOfResponse.setCode(HttpStatus.NOT_IMPLEMENTED.value());
        bodyOfResponse.setMessage("request" + req.getContextPath() + " failed");
        bodyOfResponse.setDescription("request " + req.getDescription(true) + "failed with:" + ex.getMessage());
        logger.error("restClientExceptionHandler failed when doing:" + req.getDescription(true), ex);

        return handleExceptionInternal(new RuntimeException(ex), bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_IMPLEMENTED, req);


    }

    @ExceptionHandler(EmailExistException.class)
    protected ResponseEntity<RestErrorBodyDto> restClientEmailExistsExceptionHandler(EmailExistException ex, WebRequest req) {
        RestErrorBodyDto bodyOfResponse = new RestErrorBodyDto();
        bodyOfResponse.setCode(HttpStatus.BAD_REQUEST.value());
        bodyOfResponse.setMessage("request" + req.getContextPath() + " failed");
        bodyOfResponse.setDescription("request " + req.getDescription(true) + "failed with:" + ex.getMessage());
        logger.error("restClientExceptionHandler failed when doing:" + req.getDescription(true), ex);
        List<RestErrorBodyDto.FieldError> errorList = new ArrayList<>();
        errorList.add(new RestErrorBodyDto.FieldError(400, "email", ex.getMessage()));
        bodyOfResponse.setErrors(errorList);

        return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);


    }

}
