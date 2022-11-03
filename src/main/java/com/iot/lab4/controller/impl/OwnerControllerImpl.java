package com.iot.lab4.controller.impl;

import com.iot.lab4.controller.OwnerController;
import com.iot.lab4.domain.Owner;
import com.iot.lab4.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerControllerImpl implements OwnerController {
    @Autowired
    private OwnerService ownerService;

    @Override
    public List<Owner> findAll() {
        return ownerService.findAll();
    }

    @Override
    public Optional<Owner> findById(Integer id) {
        return ownerService.findById(id);
    }

    @Override
    public int create(Owner owner) {
        return ownerService.create(owner);
    }

    @Override
    public int update(Integer id, Owner owner) {
        return ownerService.update(id, owner);
    }

    @Override
    public int delete(Integer id) {
        return ownerService.delete(id);
    }
}
