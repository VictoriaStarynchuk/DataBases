package com.iot.service.impl;

import com.iot.controller.ContinentController;
import com.iot.domain.ContinentEntity;
import com.iot.dto.ContinentEntityDto;
import com.iot.exception.ContinentNotFoundException;
import com.iot.repository.ContinentEntityRepository;
import com.iot.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ContinentServiceImpl implements ContinentService {

    @Autowired
    ContinentEntityRepository continentEntityRepository;


    public List<ContinentEntity> findAll() {
        return continentEntityRepository.findAll();
    }

    public ContinentEntity findById(Integer id) {
        return continentEntityRepository.findById(id)
                .orElseThrow(()-> new ContinentNotFoundException(id));
    }

    @Transactional
    public ContinentEntity create(ContinentEntity continent) {
        continentEntityRepository.save(continent);
        return continent;
    }

    @Transactional
    public void update(Integer id, ContinentEntity continent) {
        ContinentEntity continentEntity = continentEntityRepository.findById(id)
                .orElseThrow(()-> new ContinentNotFoundException(id));
        continentEntity.setName(continent.getName());
        continentEntityRepository.save(continentEntity);

    }

    @Transactional
    public void delete(Integer id) {
        ContinentEntity continentEntity = continentEntityRepository.findById(id)
                .orElseThrow(()-> new ContinentNotFoundException(id));
        continentEntityRepository.delete(continentEntity);


    }

    @Override
    public ContinentEntityDto insertNewValues(ContinentEntityDto continentEntityDto) {
        Integer id = continentEntityRepository.insertNewValues(continentEntityDto.getName());
        Link selfLink = linkTo(methodOn(ContinentController.class).getContinent(id)).withSelfRel();
        continentEntityDto.add(selfLink);
        return continentEntityDto;
    }

    @Override
    public void insertRows() {
        continentEntityRepository.insertRows();
    }

    @Override
    public void createTablesWithCursor() {
        continentEntityRepository.createTablesWithCursor();
    }
}
