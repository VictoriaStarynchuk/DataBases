package com.iot.exception;

public class BatteryNotFoundException extends RuntimeException{
    public BatteryNotFoundException(Integer id) { super("Could not find 'battery' with id=" + id);
    }
}
