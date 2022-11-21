package com.iot.service.impl;

import com.iot.domain.CityEntity;
import com.iot.exception.BatteryNotFoundException;
import com.iot.exception.CityNotFoundException;
import com.iot.repository.CityEntityRepository;
import com.iot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityEntityRepository cityEntityRepository;


    public List<CityEntity> findAll() {
        return cityEntityRepository.findAll();
    }


    public CityEntity findById(Integer id) {
        return cityEntityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

    @Transactional
    public CityEntity create(CityEntity city) {
        cityEntityRepository.save(city);
        return city;
    }

    @Transactional
    public void update(Integer id, CityEntity city) {
        CityEntity cityEntity = cityEntityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
        cityEntity.setName(city.getName());
        cityEntity.setCountry(city.getCountry());
        cityEntity.setBusinessLands(city.getBusinessLands());
        cityEntity.setCity(city.getCity());
        cityEntityRepository.save(cityEntity);
    }

    @Transactional
    public void delete(Integer id) {
        CityEntity cityEntity = cityEntityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
        cityEntityRepository.delete(cityEntity);
    }
}
