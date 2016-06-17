package com.bionic.edu.exception;

public class BadCredentialsException extends Exception {
    private static final long serialVersionUID = -7126607866104319067L;

    public BadCredentialsException(String message) {
        super(message);
    }
}
