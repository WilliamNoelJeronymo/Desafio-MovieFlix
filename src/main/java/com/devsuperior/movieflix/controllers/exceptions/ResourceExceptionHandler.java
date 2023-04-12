package com.devsuperior.movieflix.controllers.exceptions;

import com.devsuperior.movieflix.services.exceptions.ForbiddenException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<OAuthCustomError> forbidden(ForbiddenException e) {
        OAuthCustomError error = new OAuthCustomError("Acesso negado", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<OAuthCustomError> unauthorized(UnauthorizedException e) {
        OAuthCustomError error = new OAuthCustomError("Não autorizado",  e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError>  resourceNotFound(ResourceNotFoundException e, HttpServletRequest request)  {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error= new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Resource Not Found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status  = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError error = new ValidationError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Resource invalid");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        for(FieldError f : e.getBindingResult().getFieldErrors()) {
            error.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(error);
    }
}
