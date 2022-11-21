package com.iot.service.impl;

import com.iot.domain.OwnerEntity;
import com.iot.exception.OwnerNotFoundException;
import com.iot.repository.OwnerEntityRepository;
import com.iot.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    OwnerEntityRepository ownerEntityRepository;


    public List<OwnerEntity> findAll() {
        return ownerEntityRepository.findAll();
    }


    public OwnerEntity findById(Integer id) {
        return ownerEntityRepository.findById(id)
                .orElseThrow(()-> new OwnerNotFoundException(id));
    }

    @Transactional
    public OwnerEntity create(OwnerEntity owner) {
        ownerEntityRepository.save(owner);
        return owner;
    }

    @Transactional
    public void update(Integer id, OwnerEntity owner) {
        OwnerEntity ownerEntity = ownerEntityRepository.findById(id)
                .orElseThrow(()-> new OwnerNotFoundException(id));
        ownerEntity.setName(owner.getName());
        ownerEntity.setSurname(owner.getSurname());
        ownerEntity.setEmail(owner.getEmail());
        ownerEntity.setBusiness_lands(owner.getBusiness_lands());
        ownerEntityRepository.save(ownerEntity);
    }

    @Transactional
    public void delete(Integer id) {
        OwnerEntity ownerEntity = ownerEntityRepository.findById(id)
                .orElseThrow(()-> new OwnerNotFoundException(id));
        ownerEntityRepository.delete(ownerEntity);
    }
}
