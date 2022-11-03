package com.iot.lab4.service.impl;

import com.iot.lab4.dao.CountryDao;
import com.iot.lab4.domain.Country;
import com.iot.lab4.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;

    @Override
    public List<Country> findAll() {
        return countryDao.findAll();
    }

    @Override
    public Optional<Country> findById(Integer id) {
        return countryDao.findById(id);
    }

    @Override
    public int create(Country country) {
        return countryDao.create(country);
    }

    @Override
    public int update(Integer id, Country country) {
        return countryDao.update(id,country);
    }

    @Override
    public int delete(Integer id) {
        return countryDao.delete(id);
    }
}
