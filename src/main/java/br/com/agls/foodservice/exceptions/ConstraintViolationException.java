package br.com.agls.foodservice.exceptions;

public class ConstraintViolationException extends RuntimeException {

    public ConstraintViolationException(String message) {
        super(message);
    }
}
