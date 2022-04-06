package com.valdir.jornadaback.resources.exception;

import com.valdir.jornadaback.services.exceptions.ObjectNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

import static java.lang.String.format;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<StandardError> constraintViolationException(
            SQLIntegrityConstraintViolationException ex,
            HttpServletRequest request
    ) {
        StandardError error = StandardError.builder()
                .timestamp(now())
                .code(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message(getFieldAlreadyRegistered(ex.getMessage()))
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodNotValidArgumentException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        ValidationError error = ValidationError.builder()
                .timestamp(now())
                .code(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message("Erro na validação dos campos!")
                .path(request.getRequestURI())
                .build();

        for(FieldError x : ex.getBindingResult().getFieldErrors()) {
            error.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(
            ObjectNotFoundException ex,
            HttpServletRequest request
    ) {
        var error = StandardError.builder()
                .timestamp(now())
                .code(NOT_FOUND.value())
                .error(NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(NOT_FOUND).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> messageNotReadableException(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {
        var error = StandardError.builder()
                .timestamp(now())
                .code(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private String getFieldAlreadyRegistered(String message) {
        String[] valuesInQuotes = StringUtils.substringsBetween(message , "'", "'");
        return format("Value %s already registered in system", valuesInQuotes[0]);
    }

}
