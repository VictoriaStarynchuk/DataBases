package com.iot.controller.impl;

import com.iot.controller.BusinessLandController;
import com.iot.domain.BusinessLand;
import com.iot.service.BusinessLandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessLandControllerImpl implements BusinessLandController {
    @Autowired
    private BusinessLandService businessLandService;

    @Override
    public List<BusinessLand> findAll() {
        return businessLandService.findAll();
    }

    @Override
    public Optional<BusinessLand> findById(Integer id) {
        return businessLandService.findById(id);
    }

    @Override
    public int create(BusinessLand businessLand) {
        return businessLandService.create(businessLand);
    }

    @Override
    public int update(Integer id, BusinessLand businessLand) {
        return businessLandService.update(id, businessLand);
    }

    @Override
    public int delete(Integer id) {
        return businessLandService.delete(id);
    }

}
