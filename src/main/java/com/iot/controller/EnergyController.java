package com.iot.controller;

import com.iot.domain.CountryEntity;
import com.iot.domain.EnergyEntity;
import com.iot.dto.CountryEntityDto;
import com.iot.dto.EnergyEntityDto;
import com.iot.dto.assembler.EnergyDtoAssembler;
import com.iot.service.EnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/someEnergy")
public class EnergyController {
    @Autowired
    EnergyService energyService;
    @Autowired
    EnergyDtoAssembler energyDtoAssembler;

    @GetMapping(value = "{energyId}")
    public ResponseEntity<EnergyEntityDto> getEnergy(@PathVariable Integer energyId){
        EnergyEntity energyEntity = energyService.findById(energyId);
        EnergyEntityDto energyEntityDto = energyDtoAssembler.toModel(energyEntity);
        return new ResponseEntity<>(energyEntityDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<EnergyEntityDto>> getAllEnergy(){
        List<EnergyEntity> energyEntities = energyService.findAll();
        CollectionModel<EnergyEntityDto> energyEntityDtos = energyDtoAssembler.toCollectionModel(energyEntities);
        return new ResponseEntity<>(energyEntityDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<EnergyEntityDto> addEnergy(@RequestBody EnergyEntity energyEntity){
        EnergyEntity newEnergy = energyService.create(energyEntity);
        EnergyEntityDto energyEntityDto = energyDtoAssembler.toModel(energyEntity);
        return new ResponseEntity<>(energyEntityDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{energyId}")
    public ResponseEntity<?> updateEnergy(@RequestBody EnergyEntity uEnergy, @PathVariable Integer energyId){
        energyService.update(energyId, uEnergy);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "/{energyId}")
    public ResponseEntity<?> deleteEnergy(@PathVariable Integer energyId){
        energyService.delete((energyId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
