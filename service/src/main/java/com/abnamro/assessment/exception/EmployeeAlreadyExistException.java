package com.abnamro.assessment.exception;

public class EmployeeAlreadyExistException extends RuntimeException {

    private String message;
    public EmployeeAlreadyExistException() {
        super();
    }

    public EmployeeAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }

}
