package com.iot.service.impl;

import com.iot.domain.EnergyMarketEntity;
import com.iot.exception.BatteryNotFoundException;
import com.iot.exception.EnergyMarketNotFoundException;
import com.iot.repository.EnergyMarketEntityRepository;
import com.iot.service.EnergyMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnergyMarketServiceImpl implements EnergyMarketService {
    @Autowired
    EnergyMarketEntityRepository energyMarketEntityRepository;


    public List<EnergyMarketEntity> findAll() {
        return energyMarketEntityRepository.findAll();
    }

    public EnergyMarketEntity findById(Integer id) {
        return energyMarketEntityRepository.findById(id)
                .orElseThrow(()-> new  EnergyMarketNotFoundException(id));
    }

    @Transactional
    public EnergyMarketEntity create(EnergyMarketEntity energyMarket) {
        energyMarketEntityRepository.save(energyMarket);
        return energyMarket;
    }

    @Transactional
    public void update(Integer id, EnergyMarketEntity energyMarket) {
        EnergyMarketEntity energyMarketEntity = energyMarketEntityRepository.findById(id)
                .orElseThrow(()-> new EnergyMarketNotFoundException(id));
        energyMarketEntity.setEnergy(energyMarket.getEnergy());
        energyMarketEntity.setDate(energyMarket.getDate());
        energyMarketEntity.setPrice(energyMarket.getPrice());
        energyMarketEntity.setTime(energyMarket.getTime());
        energyMarketEntityRepository.save(energyMarketEntity);

    }

    @Transactional
    public void delete(Integer id) {
        EnergyMarketEntity energyMarketEntity = energyMarketEntityRepository.findById(id)
                .orElseThrow(()-> new  EnergyMarketNotFoundException(id));
        energyMarketEntityRepository.delete(energyMarketEntity);
    }
}
