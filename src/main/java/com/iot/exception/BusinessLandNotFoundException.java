package com.iot.exception;

public class BusinessLandNotFoundException extends RuntimeException {
    public BusinessLandNotFoundException(Integer id) { super("Could not find 'businessLand' with id=" + id);
    }
}
