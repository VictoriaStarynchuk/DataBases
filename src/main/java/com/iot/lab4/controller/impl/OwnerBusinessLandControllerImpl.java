package com.iot.lab4.controller.impl;

import com.iot.lab4.controller.OwnerBusinessLandController;
import com.iot.lab4.domain.OwnerBusinessLand;
import com.iot.lab4.service.OwnerBusinessLandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerBusinessLandControllerImpl implements OwnerBusinessLandController {
    @Autowired
    private OwnerBusinessLandService ownerBusinessLandService;

    @Override
    public List<OwnerBusinessLand> findAll() {
        return ownerBusinessLandService.findAll();
    }

    @Override
    public Optional<OwnerBusinessLand> findById(Integer id) {
        return ownerBusinessLandService.findById(id);
    }

    @Override
    public int create(OwnerBusinessLand ownerBusinessLand) {
        return ownerBusinessLandService.create(ownerBusinessLand);
    }

    @Override
    public int update(Integer id, OwnerBusinessLand ownerBusinessLand) {
        return ownerBusinessLandService.update(id,ownerBusinessLand);
    }

    @Override
    public int delete(Integer id) {
        return ownerBusinessLandService.delete(id);
    }
}
