package com.iot.exception;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(Integer id) {super("Could not find 'element' with id=" + id);
    }
}
