package com.iot.service.impl;

import com.iot.domain.BusinessLandEntity;
import com.iot.exception.BusinessLandNotFoundException;
import com.iot.repository.BusinessLandEntityRepository;
import com.iot.service.BusinessLandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BusinessLandServiceImpl implements BusinessLandService {
    @Autowired
    BusinessLandEntityRepository businessLandEntityRepository;


    public List<BusinessLandEntity> findAll() {
        return businessLandEntityRepository.findAll();
    }


    public BusinessLandEntity findById(Integer id) {
        return businessLandEntityRepository.findById(id)
                .orElseThrow(() -> new BusinessLandNotFoundException(id));
    }

    @Transactional
    public BusinessLandEntity create(BusinessLandEntity businessLand) {
        businessLandEntityRepository.save(businessLand);
        return businessLand;
    }

    @Transactional
    public void update(Integer id, BusinessLandEntity businessLand) {
        BusinessLandEntity businessLandEntity = businessLandEntityRepository.findById(id)
                .orElseThrow(() -> new BusinessLandNotFoundException(id));
        businessLandEntity.setAddress(businessLand.getAddress());
        businessLandEntity.setCity(businessLand.getCity());
        businessLandEntity.setOwners(businessLand.getOwners());
        businessLandEntity.setQuantityStation(businessLand.getQuantityStation());
        businessLandEntity.setStations(businessLand.getStations());
        businessLandEntityRepository.save(businessLandEntity);
    }

    @Transactional
    public void delete(Integer id) {
        BusinessLandEntity businessLandEntity = businessLandEntityRepository.findById(id)
                .orElseThrow(() -> new BusinessLandNotFoundException(id));
        businessLandEntityRepository.delete(businessLandEntity);
    }
}
