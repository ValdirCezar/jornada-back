package com.valdir.userservice.resources.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

import static java.lang.String.format;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<StandardError> constraintViolationException(
            SQLIntegrityConstraintViolationException ex,
            HttpServletRequest request
    ) {
        StandardError error = new StandardError(
                now(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                getFieldAlreadyRegistered(ex.getMessage()),
                request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    private String getFieldAlreadyRegistered(String message) {
        String[] valuesInQuotes = StringUtils.substringsBetween(message , "'", "'");
        return format("Value %s already registered in system", valuesInQuotes[0]);
    }

}
