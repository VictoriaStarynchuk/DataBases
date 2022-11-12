package com.iot.exception;

public class StationNotFoundException extends RuntimeException {
    public StationNotFoundException(Integer id) {super("Could not find 'station' with id=" + id);
    }
}
