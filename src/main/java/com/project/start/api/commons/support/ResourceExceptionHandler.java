package com.project.start.api.commons.support;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.project.start.api.commons.messages.MessageManager;
import com.project.start.api.commons.support.exceptions.BusinessException;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
@RequiredArgsConstructor
public class ResourceExceptionHandler {

	private final MessageManager messages;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseError handleValidationExceptions(MethodArgumentNotValidException ex) {
		log.error(ex);
		
        var responseError = ResponseError.builder()
                                         .code(HttpStatus.BAD_REQUEST.value())
                                         .description(messages.get("vdt.err.campo_invalido"))
                                         .build();

        ex.getBindingResult().getAllErrors().forEach(e -> {
            String fieldName = ((FieldError) e).getField();
            String errorMessage = e.getDefaultMessage();
            responseError.getFields().put(fieldName, errorMessage);
        });

        return responseError;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseError handleConstraintViolationException(ConstraintViolationException ex) {
		log.error(ex);
        var responseError = ResponseError.builder()
                                         .code(HttpStatus.BAD_REQUEST.value())
                                         .description(ex.getMessage())
                                         .fields(new HashMap<>())
                                         .build();

        ex.getConstraintViolations().forEach(cv -> {
            String fieldName = cv.getPropertyPath() == null ? "" : cv.getPropertyPath().toString();
            String errorMessage = cv.getMessage();
            responseError.getFields().put(fieldName, errorMessage);
        });

        return responseError;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseError handleExpiredJwtException(ExpiredJwtException e) {
		log.error(e);
		
        return ResponseError.builder()
                            .code(HttpStatus.FORBIDDEN.value())
                            .description(messages.get("auth.err.expirado"))
                            .build();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseError handleBadCredentialsException(BadCredentialsException e) {
		log.error(e);
		
        return ResponseError.builder()
                            .code(HttpStatus.FORBIDDEN.value())
                            .description(messages.get("auth.err.credenciais_invalidas"))
                            .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public ResponseError handleBusinessException(BusinessException e) {
		log.error(e);
		
        return ResponseError.builder()
                            .code(HttpStatus.BAD_REQUEST.value())
                            .description(e.getMessage())
                            .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseError handleNotFoundExceptions(NoSuchElementException ex) {
		log.error(ex);
		
        return ResponseError.builder()
                            .code(HttpStatus.NOT_FOUND.value())
                            .description(messages.get("sys.err.nao_encontrado"))
                            .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseError handleException(Exception e) {
		log.error(e);
		
        return ResponseError.builder()
                            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .description(messages.get("sys.err.inesperado"))
                            .build();
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleValidationExceptions(ResponseStatusException ex) {
		log.error(ex);
        return new ResponseEntity<>(ResponseError.builder()
                                                 .code(ex.getStatusCode().value())
                                                 .description(ex.getMessage())
                                                 .build(), ex.getStatusCode());
    }

}
