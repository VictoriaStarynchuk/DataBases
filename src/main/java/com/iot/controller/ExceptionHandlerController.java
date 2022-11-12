package com.iot.controller;

import com.iot.domain.CountryEntity;
import com.iot.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(BatteryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String batteryNotFoundHandler(BatteryNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BusinessLandNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String businessLandNotFoundHandler(BusinessLandNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cityNotFoundHandler(CityNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String countryNotFoundHandler(CountryNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EnergyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String energyNotFoundHandler(EnergyNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EnergyMarketNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String energyMarketNotFoundHandler(EnergyMarketNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ElementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String elementMarketNotFoundHandler(ElementNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(LogNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String logNotFoundHandler(LogNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(OwnerNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String ownerNotFoundHandler(OwnerNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PanelNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String panelNotFoundHandler(PanelNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(StationNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String stationNotFoundHandler(StationNotFoundException ex) {
        return ex.getMessage();
    }
}
