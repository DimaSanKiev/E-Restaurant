package com.bionic.edu.exception;

public class EmployeeUnavailableException extends Exception {
    private static final long serialVersionUID = -4714362136917873794L;

    public EmployeeUnavailableException(String message) {
        super(message);
    }
}
