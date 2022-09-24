package com.helpdesk.springangularproject.service.exception;

import java.io.Serial;

public class DataIntegrityViolationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8739492550540406468L;

    public DataIntegrityViolationException(String message) {
        super(message);
    }

    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
