package com.iot.lab4.controller.impl;

import com.iot.lab4.controller.EnergyMarketController;
import com.iot.lab4.domain.EnergyMarket;
import com.iot.lab4.service.EnergyMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnergyMarketControllerImpl implements EnergyMarketController {
    @Autowired
    private EnergyMarketService energyMarketService;

    @Override
    public List<EnergyMarket> findAll() {
        return energyMarketService.findAll();
    }

    @Override
    public Optional<EnergyMarket> findById(Integer id) {
        return energyMarketService.findById(id);
    }

    @Override
    public int create(EnergyMarket energyMarket) {
        return energyMarketService.create(energyMarket);
    }

    @Override
    public int update(Integer id, EnergyMarket energyMarket) {
        return energyMarketService.update(id, energyMarket);
    }

    @Override
    public int delete(Integer id) {
        return energyMarketService.delete(id);
    }
}
