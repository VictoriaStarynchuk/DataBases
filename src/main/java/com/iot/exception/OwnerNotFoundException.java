package com.iot.exception;

public class OwnerNotFoundException extends RuntimeException {
    public OwnerNotFoundException(Integer id) {super("Could not find 'owner' with id=" + id);
    }
}
