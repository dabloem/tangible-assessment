package com.abnamro.assessment.exception;

public class EmployeeNameInvalidException extends RuntimeException {
    private String message;
    public EmployeeNameInvalidException() {
        super();
    }

    public EmployeeNameInvalidException(String message) {
        super(message);
        this.message = message;
    }

}
