package com.bionic.edu.util;

public class CustomerBlockedException extends RuntimeException {
    private static final long serialVersionUID = -4714362136917873794L;

    public CustomerBlockedException(String message) {
        super(message);
    }
}
