package com.iot.service.impl;

import com.iot.dao.EnergyDao;
import com.iot.domain.Energy;
import com.iot.service.EnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnergyServiceImpl implements EnergyService {
    @Autowired
    private EnergyDao energyDao;

    @Override
    public List<Energy> findAll() {
        return energyDao.findAll();
    }

    @Override
    public Optional<Energy> findById(Integer id) {
        return energyDao.findById(id);
    }

    @Override
    public int create(Energy energy) {
        return energyDao.create(energy);
    }

    @Override
    public int update(Integer id, Energy energy) {
        return energyDao.update(id, energy);
    }

    @Override
    public int delete(Integer id) {
        return energyDao.delete(id);
    }

}
