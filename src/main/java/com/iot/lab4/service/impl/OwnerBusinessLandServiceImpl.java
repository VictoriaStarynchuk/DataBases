package com.iot.lab4.service.impl;

import com.iot.lab4.dao.OwnerBusinessLandDao;
import com.iot.lab4.domain.OwnerBusinessLand;
import com.iot.lab4.service.OwnerBusinessLandService;
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
