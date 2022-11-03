package com.iot.lab4.controller.impl;

import com.iot.lab4.controller.EnergyController;
import com.iot.lab4.domain.Energy;
import com.iot.lab4.service.EnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnergyControllerImpl implements EnergyController {
    @Autowired
    private EnergyService energyService;

    @Override
    public List<Energy> findAll() {
        return energyService.findAll();
    }

    @Override
    public Optional<Energy> findById(Integer id) {
        return energyService.findById(id);
    }

    @Override
    public int create(Energy energy) {
        return energyService.create(energy);
    }

    @Override
    public int update(Integer id, Energy energy) {
        return energyService.update(id,energy);
    }

    @Override
    public int delete(Integer id) {
        return energyService.delete(id);
    }
}
