package com.iot.controller.impl;

import com.iot.controller.CityController;
import com.iot.domain.City;
import com.iot.service.CityService;
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
