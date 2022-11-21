package com.iot.service.impl;

import com.iot.dao.OwnerDao;
import com.iot.domain.Owner;
import com.iot.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerDao ownerDao;

    @Override
    public List<Owner> findAll() {
        return ownerDao.findAll();
    }

    @Override
    public Optional<Owner> findById(Integer id) {
        return ownerDao.findById(id);
    }

    @Override
    public int create(Owner owner) {
        return ownerDao.create(owner);
    }

    @Override
    public int update(Integer id, Owner owner) {
        return ownerDao.update(id, owner);
    }

    @Override
    public int delete(Integer id) {
        return ownerDao.delete(id);
    }

}
