package br.com.agls.foodservice.api.handlererror;

import br.com.agls.foodservice.exceptions.ConstraintViolationException;
import br.com.agls.foodservice.exceptions.DataBaseOperationException;
import br.com.agls.foodservice.exceptions.EntityNotFoundException;
import br.com.agls.foodservice.exceptions.InternalServerErrorException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InternalServerErrorException.class})
    public ResponseEntity<Object> handleInternalServerErrorException (RuntimeException e, WebRequest request) {

        HttpStatus requestStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        ResponseError response = ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .status(requestStatus.value())
                .message(e.getMessage())
                .build();

        return handleExceptionInternal(e, response, new HttpHeaders(), requestStatus, request);
    }

    @ExceptionHandler(value = {DataBaseOperationException.class})
    public ResponseEntity<Object> handleDataBaseOperationException (RuntimeException e, WebRequest request) {

        HttpStatus requestStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        ResponseError response = ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .status(requestStatus.value())
                .message(e.getMessage())
                .build();

        return handleExceptionInternal(e, response, new HttpHeaders(), requestStatus, request);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException (RuntimeException e, WebRequest request) {

        HttpStatus requestStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        ResponseError response = ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .status(requestStatus.value())
                .message(e.getMessage())
                .build();

        return handleExceptionInternal(e, response, new HttpHeaders(), requestStatus, request);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException (RuntimeException e, WebRequest request) {

        HttpStatus requestStatus = HttpStatus.NOT_FOUND;

        ResponseError response = ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .status(requestStatus.value())
                .message(e.getMessage())
                .build();

        return handleExceptionInternal(e, response, new HttpHeaders(), requestStatus, request);
    }
}
