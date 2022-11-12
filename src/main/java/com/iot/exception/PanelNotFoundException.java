package com.iot.exception;

public class PanelNotFoundException extends RuntimeException {
    public PanelNotFoundException(Integer id) {super("Could not find 'panel' with id=" + id);
    }
}
