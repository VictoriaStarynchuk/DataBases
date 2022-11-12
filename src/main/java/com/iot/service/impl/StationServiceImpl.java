package com.iot.service.impl;

import com.iot.domain.StationEntity;
import com.iot.exception.StationNotFoundException;
import com.iot.repository.StationEntityRepository;
import com.iot.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {
    @Autowired
    StationEntityRepository stationEntityRepository;

    public List<StationEntity> findAll() {
        return stationEntityRepository.findAll();
    }

    public StationEntity findById(Integer id) {
        return stationEntityRepository.findById(id)
                .orElseThrow(()-> new StationNotFoundException(id));
    }

    @Transactional
    public StationEntity create(StationEntity station) {
        stationEntityRepository.save(station);
        return station;
    }

    public void update(Integer id, StationEntity station) {
        StationEntity stationEntity = stationEntityRepository.findById(id)
                .orElseThrow(()-> new StationNotFoundException(id));
        stationEntity.setEnergy(station.getEnergy());
        stationEntity.setAreaSqKm(station.getAreaSqKm());
        stationEntity.setBusinessLand(station.getBusinessLand());
        stationEntity.setElement(station.getElement());
        stationEntityRepository.save(stationEntity);
    }

    public void delete(Integer id) {
        StationEntity stationEntity = stationEntityRepository.findById(id)
                .orElseThrow(()-> new StationNotFoundException(id));
        stationEntityRepository.delete(stationEntity);
    }
}
