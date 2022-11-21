package com.iot.service.impl;

import com.iot.dao.EnergyMarketDao;
import com.iot.domain.EnergyMarket;
import com.iot.service.EnergyMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnergyMarketServiceImpl implements EnergyMarketService {
    @Autowired
    private EnergyMarketDao energyMarketDao;

    @Override
    public List<EnergyMarket> findAll() {
        return energyMarketDao.findAll();
    }

    @Override
    public Optional<EnergyMarket> findById(Integer id) {
        return energyMarketDao.findById(id);
    }

    @Override
    public int create(EnergyMarket energyMarket) {
        return energyMarketDao.create(energyMarket);
    }

    @Override
    public int update(Integer id, EnergyMarket energyMarket) {
        return energyMarketDao.update(id, energyMarket);
    }

    @Override
    public int delete(Integer id) {
        return energyMarketDao.delete(id);
    }

}
