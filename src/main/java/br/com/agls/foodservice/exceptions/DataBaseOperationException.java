package br.com.agls.foodservice.exceptions;

public class DataBaseOperationException extends RuntimeException {

    public DataBaseOperationException(String message) {
        super(message);
    }
}
