package com.iot.service.impl;

import com.iot.domain.EnergyEntity;
import com.iot.exception.EnergyNotFoundException;
import com.iot.repository.EnergyEntityRepository;
import com.iot.service.EnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnergyServiceImpl implements EnergyService {
    @Autowired
    EnergyEntityRepository energyEntityRepository;


    public List<EnergyEntity> findAll() {
        return energyEntityRepository.findAll();
    }

    public EnergyEntity findById(Integer id) {
        return energyEntityRepository.findById(id)
                .orElseThrow(()-> new EnergyNotFoundException(id));
    }

    @Transactional
    public EnergyEntity create(EnergyEntity energy) {
         energyEntityRepository.save(energy);
        return energy;
    }

    @Transactional
    public void update(Integer id, EnergyEntity energy) {
        EnergyEntity energyEntity = energyEntityRepository.findById(id)
                .orElseThrow(()-> new EnergyNotFoundException(id));
        energyEntity.setEnergyMarkets(energy.getEnergyMarkets());
        energyEntity.setStations(energy.getStations());
        energyEntity.setSolarAmount(energy.getSolarAmount());
        energyEntity.setExporting(energy.getExporting());
        energyEntity.setUseNow(energy.getUseNow());
        energyEntityRepository.save(energyEntity);

    }

    @Transactional
    public void delete(Integer id) {
        EnergyEntity energyEntity = energyEntityRepository.findById(id)
                .orElseThrow(()-> new EnergyNotFoundException(id));
        energyEntityRepository.delete(energyEntity);
    }
}
