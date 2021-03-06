package com.valdir.jornadaback.resources.exception;

import com.valdir.jornadaback.services.exceptions.DataIntegrityViolationException;
import com.valdir.jornadaback.services.exceptions.FileNotSupportedException;
import com.valdir.jornadaback.services.exceptions.ObjectNotFoundException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ResourceExceptionHandler {

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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(
            DataIntegrityViolationException ex,
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

    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ResponseEntity<StandardError> fileSizeLimitExceededException(
            FileSizeLimitExceededException ex,
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

    @ExceptionHandler(FileNotSupportedException.class)
    public ResponseEntity<StandardError> fileNotSupportedException(
            FileNotSupportedException ex,
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
}
