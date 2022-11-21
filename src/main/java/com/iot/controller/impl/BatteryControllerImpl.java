package com.iot.controller.impl;

import com.iot.controller.BatteryController;
import com.iot.domain.Battery;
import com.iot.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatteryControllerImpl implements BatteryController {
    @Autowired
    private BatteryService batteryService;

    @Override
    public List<Battery> findAll() {
        return batteryService.findAll();
    }

    @Override
    public Optional<Battery> findById(Integer id) {
        return batteryService.findById(id);
    }

    @Override
    public int create(Battery battery) {
        return batteryService.create(battery);
    }

    @Override
    public int update(Integer id, Battery battery) {
        return batteryService.update(id, battery);
    }

    @Override
    public int delete(Integer id) {
        return batteryService.delete(id);
    }

}
