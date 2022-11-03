package com.iot.lab4.controller.impl;

import com.iot.lab4.controller.CityController;
import com.iot.lab4.domain.City;
import com.iot.lab4.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityControllerImpl implements CityController {
    @Autowired
    private CityService cityService;

    @Override
    public List<City> findAll() {
        return cityService.findAll();
    }

    @Override
    public Optional<City> findById(Integer id) {
        return cityService.findById(id);
    }

    @Override
    public int create(City city) {
        return cityService.create(city);
    }

    @Override
    public int update(Integer id, City city) {
        return cityService.update(id, city);
    }

    @Override
    public int delete(Integer id) {
        return cityService.delete(id);
    }
}
