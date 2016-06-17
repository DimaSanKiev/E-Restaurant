package com.bionic.edu.exception;

public class EmployeeNotReadyException extends Exception {
    private static final long serialVersionUID = -4714362136917873794L;

    public EmployeeNotReadyException(String message) {
        super(message);
    }
}
