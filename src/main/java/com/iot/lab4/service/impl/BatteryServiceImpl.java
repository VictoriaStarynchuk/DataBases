package com.iot.lab4.service.impl;

import com.iot.lab4.dao.BatteryDao;
import com.iot.lab4.domain.Battery;
import com.iot.lab4.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatteryServiceImpl implements BatteryService {
    @Autowired
    private BatteryDao batteryDao;

    @Override
    public List<Battery> findAll() {
        return batteryDao.findAll();
    }

    @Override
    public Optional<Battery> findById(Integer id) {
        return batteryDao.findById(id);
    }

    @Override
    public int create(Battery battery) {
        return batteryDao.create(battery);
    }

    @Override
    public int update(Integer id, Battery battery) {
        return batteryDao.update(id, battery );
    }

    @Override
    public int delete(Integer id) {
        return batteryDao.delete(id);
    }
}
