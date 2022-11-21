package com.iot.service.impl;

import com.iot.dao.OwnerBusinessLandDao;
import com.iot.domain.OwnerBusinessLand;
import com.iot.service.OwnerBusinessLandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerBusinessLandServiceImpl implements OwnerBusinessLandService {
    @Autowired
    private OwnerBusinessLandDao ownerBusinessLandDao;

    @Override
    public List<OwnerBusinessLand> findAll() {
        return ownerBusinessLandDao.findAll();
    }

    @Override
    public Optional<OwnerBusinessLand> findById(Integer id) {
        return ownerBusinessLandDao.findById(id);
    }

    @Override
    public int create(OwnerBusinessLand ownerBusinessLand) {
        return ownerBusinessLandDao.create(ownerBusinessLand);
    }

    @Override
    public int update(Integer id, OwnerBusinessLand ownerBusinessLand) {
        return ownerBusinessLandDao.update(id, ownerBusinessLand);
    }

    @Override
    public int delete(Integer id) {
        return ownerBusinessLandDao.delete(id);
    }

}
