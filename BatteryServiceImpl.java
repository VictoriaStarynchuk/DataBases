package com.iot.service.impl;

import com.iot.domain.BatteryEntity;
import com.iot.exception.BatteryNotFoundException;
import com.iot.repository.BatteryEntityRepository;
import com.iot.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BatteryServiceImpl implements BatteryService {
    @Autowired
    BatteryEntityRepository batteryEntityRepository;

    public List<BatteryEntity> findAll() {
        return batteryEntityRepository.findAll();
    }

    public BatteryEntity findById(Integer id) {
        return batteryEntityRepository.findById(id)
                .orElseThrow(() -> new BatteryNotFoundException(id));
    }

    @Transactional
    public BatteryEntity create(BatteryEntity battery) {
        batteryEntityRepository.save(battery);
        return battery;
    }

    @Transactional
    public void update(Integer id, BatteryEntity battery) {
        BatteryEntity batteryEntity = batteryEntityRepository.findById(id)
                .orElseThrow(() -> new BatteryNotFoundException(id));
        batteryEntity.setType(battery.getType());
        batteryEntity.setCapacity(battery.getCapacity());
        batteryEntity.setChargeLevel(battery.getChargeLevel());
        batteryEntity.setElements(battery.getElements());
        batteryEntity.setPower(battery.getPower());
        batteryEntity.setDurationTime(battery.getDurationTime());
        batteryEntityRepository.save(batteryEntity);

    }

    @Transactional
    public void delete(Integer id) {
        BatteryEntity batteryEntity = batteryEntityRepository.findById(id)
                .orElseThrow(()-> new BatteryNotFoundException(id)) ;
        batteryEntityRepository.delete(batteryEntity);
        }

}

