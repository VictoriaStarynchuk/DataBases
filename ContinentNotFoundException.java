package com.iot.exception;

public class ContinentNotFoundException extends RuntimeException {
    public ContinentNotFoundException(Integer id) {
        super("Could not find 'continent' with id=" + id);

    }
}
