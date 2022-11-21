package com.iot.service.impl;

import com.iot.domain.CountryEntity;
import com.iot.exception.BatteryNotFoundException;
import com.iot.exception.CountryNotFoundException;
import com.iot.repository.CountryEntityRepository;
import com.iot.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryEntityRepository countryEntityRepository;

    public List<CountryEntity> findAll() {
        return countryEntityRepository.findAll();
    }

    public CountryEntity findById(Integer id) {
        return countryEntityRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Transactional
    public CountryEntity create(CountryEntity country) {
        countryEntityRepository.save(country);
        return country;
    }

    @Transactional
    public void update(Integer id, CountryEntity country) {
        CountryEntity countryEntity = countryEntityRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
        countryEntity.setName(country.getName());
        countryEntity.setCities(country.getCities());
        countryEntityRepository.save(countryEntity);
    }

    @Transactional
    public void delete(Integer id) {
        CountryEntity countryEntity = countryEntityRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
        countryEntityRepository.delete(countryEntity);
    }
}
