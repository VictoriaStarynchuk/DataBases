package com.iot.exception;

public class EnergyNotFoundException extends RuntimeException {
    public EnergyNotFoundException(Integer id) {super("Could not find 'energy' with id=" + id);
    }
}
