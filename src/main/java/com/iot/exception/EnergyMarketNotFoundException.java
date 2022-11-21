package com.iot.exception;

public class EnergyMarketNotFoundException extends RuntimeException {
    public EnergyMarketNotFoundException(Integer id) {super("Could not find 'energyMarket' with id=" + id);
    }
}
