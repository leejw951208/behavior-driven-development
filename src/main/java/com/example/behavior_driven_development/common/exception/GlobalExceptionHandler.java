package com.example.behavior_driven_development.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ResponseStatusExceptionDto> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request) {
        ResponseStatusExceptionDto exceptionDto = new ResponseStatusExceptionDto(
                HttpStatus.valueOf(ex.getStatusCode().value()).name(),
                request.getRequestURL().toString(),
                ex.getReason(),
                ex.getStatusCode().value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        log.error(ex.getReason());
        return ResponseEntity.status(ex.getStatusCode()).body(exceptionDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseStatusExceptionDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        String defaultMessage = bindingResult.getFieldError() != null ? bindingResult.getFieldError().getDefaultMessage() : "Not found error";

        ResponseStatusExceptionDto exceptionDto = new ResponseStatusExceptionDto(
                HttpStatus.valueOf(ex.getStatusCode().value()).name(),
                request.getRequestURL().toString(),
                defaultMessage,
                ex.getStatusCode().value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode().value()).body(exceptionDto);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ResponseStatusExceptionDto> handleMethodArgumentNotValidException(HttpMediaTypeNotSupportedException ex, HttpServletRequest request) {
        ResponseStatusExceptionDto exceptionDto = new ResponseStatusExceptionDto(
                HttpStatus.valueOf(ex.getStatusCode().value()).name(),
                request.getRequestURL().toString(),
                ex.getMessage(),
                ex.getStatusCode().value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode().value()).body(exceptionDto);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseStatusExceptionDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        ResponseStatusExceptionDto dto = new ResponseStatusExceptionDto(
                HttpStatus.BAD_REQUEST.name(),
                request.getRequestURL().toString(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseStatusExceptionDto> handleMethodArgumentNotValidException(IllegalArgumentException ex, HttpServletRequest request) {
        ResponseStatusExceptionDto exceptionDto = new ResponseStatusExceptionDto(
                HttpStatus.BAD_REQUEST.name(),
                request.getRequestURL().toString(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(exceptionDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseStatusExceptionDto> handleException(Exception ex, HttpServletRequest request) {
        ResponseStatusExceptionDto exceptionDto = new ResponseStatusExceptionDto(
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                request.getRequestURL().toString(),
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(exceptionDto);
    }
}
