package com.iot.service.impl;

import com.iot.dao.BusinessLandDao;
import com.iot.domain.BusinessLand;
import com.iot.service.BusinessLandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessLandServiceImpl implements BusinessLandService {
    @Autowired
    private BusinessLandDao businessLandDao;

    @Override
    public List<BusinessLand> findAll() {
        return businessLandDao.findAll();
    }

    @Override
    public Optional<BusinessLand> findById(Integer id) {
        return businessLandDao.findById(id);
    }

    @Override
    public int create(BusinessLand businessLand) {
        return businessLandDao.create(businessLand);
    }

    @Override
    public int update(Integer id, BusinessLand businessLand) {
        return businessLandDao.update(id,businessLand);
    }

    @Override
    public int delete(Integer id) {
        return businessLandDao.delete(id);
    }

}
