package com.example.rea4e.rest.controller;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.rea4e.domain.exception.*;

import jakarta.persistence.EntityNotFoundException;

/*
 * Essa classe é responsáveis por tratar as exceções lançadas pela aplicação e traduzir nos codigos de status HTTP corretos
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // Classe interna para padronizar o formato de erro
    public record ErrorResponse(
        HttpStatus status,
        String errorCode,
        String message,
        LocalDateTime timestamp
    ) {
        public ErrorResponse(HttpStatus status, String errorCode, String message) {
            this(status, errorCode, message, LocalDateTime.now());
        }
    }

    // Exceções de Permissões
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ErrorResponse> handleConflictExceptions(RuntimeException ex) {
        return this.buildResponse(
            HttpStatus.CONFLICT,
            "RESOURCE_CONFLICT",
            ex.getMessage()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestExceptions(RuntimeException ex) {
        return this.buildResponse(
            HttpStatus.BAD_REQUEST,
            "INVALID_REQUEST",
            ex.getMessage()
        );
    }

    @ExceptionHandler({
        EntityNotFoundException.class,
        ValueNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions(RuntimeException ex) {
        return this.buildResponse(
            HttpStatus.NOT_FOUND,
            "RESOURCE_NOT_FOUND",
            ex.getMessage()
        );
    }



    // Tratamento para exceções de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining("; "));

        return this.buildResponse(
            HttpStatus.BAD_REQUEST,
            "VALIDATION_ERROR",
            errorMessage
        );
    }

    // // Catch-all para exceções não mapeadas
    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
    //     return this.buildResponse(
    //         HttpStatus.INTERNAL_SERVER_ERROR,
    //         "INTERNAL_ERROR",
    //         "Ocorreu um erro inesperado"
    //     );
    // }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String errorCode, String message) {
        return ResponseEntity
            .status(status)
            .body(new ErrorResponse(status, errorCode, message));
    }
}
