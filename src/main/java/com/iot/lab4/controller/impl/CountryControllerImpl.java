package com.iot.lab4.controller.impl;

import com.iot.lab4.controller.CountryController;
import com.iot.lab4.domain.Country;
import com.iot.lab4.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryControllerImpl implements CountryController {
    @Autowired
    private CountryService countryService;

    @Override
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @Override
    public Optional<Country> findById(Integer id) {
        return countryService.findById(id);
    }

    @Override
    public int create(Country country) {
        return countryService.create(country);
    }

    @Override
    public int update(Integer id, Country country) {
        return countryService.update(id, country);
    }

    @Override
    public int delete(Integer id) {
        return countryService.delete(id);
    }
}
