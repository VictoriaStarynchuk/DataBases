package com.iot.exception;

public class LogNotFoundException extends RuntimeException {
    public LogNotFoundException(Integer id) {
        super("Could not find 'log' with id=" + id);
    }
}
