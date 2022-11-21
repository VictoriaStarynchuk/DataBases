package com.iot.controller;

import com.iot.domain.EnergyEntity;
import com.iot.domain.EnergyMarketEntity;
import com.iot.dto.EnergyEntityDto;
import com.iot.dto.EnergyMarketEntityDto;
import com.iot.dto.assembler.EnergyMarketDtoAssembler;
import com.iot.service.EnergyMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/energyMarkets")
public class EnergyMarketController {
    @Autowired
    EnergyMarketService energyMarketService;
    @Autowired
    EnergyMarketDtoAssembler energyMarketDtoAssembler;

    @GetMapping(value = "{energyMarketId}")
    public ResponseEntity<EnergyMarketEntityDto> getEnergyMarket(@PathVariable Integer energyMarketId){
        EnergyMarketEntity energyMarketEntity = energyMarketService.findById(energyMarketId);
        EnergyMarketEntityDto energyMarketEntityDto  = energyMarketDtoAssembler.toModel(energyMarketEntity);
        return new ResponseEntity<>(energyMarketEntityDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<EnergyMarketEntityDto>> getAllEnergyMarkets(){
        List<EnergyMarketEntity> energyMarketEntities = energyMarketService.findAll();
        CollectionModel<EnergyMarketEntityDto> energyMarketEntityDtos = energyMarketDtoAssembler.toCollectionModel(energyMarketEntities);
        return new ResponseEntity<>(energyMarketEntityDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<EnergyMarketEntityDto> addEnergyMarket(@RequestBody EnergyMarketEntity energyMarketEntity){
        EnergyMarketEntity newMarketEntity = energyMarketService.create(energyMarketEntity);
        EnergyMarketEntityDto energyMarketEntityDto = energyMarketDtoAssembler.toModel(energyMarketEntity);
        return new ResponseEntity<>(energyMarketEntityDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{energyMarketId}")
    public ResponseEntity<?> updateEnergyMarket(@RequestBody EnergyMarketEntity uEnergyMarket, @PathVariable Integer energyMarketId){
        energyMarketService.update(energyMarketId, uEnergyMarket);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "/{energyMarketId}")
    public ResponseEntity<?> deleteEnergyMarket(@PathVariable Integer energyMarketId){
        energyMarketService.delete((energyMarketId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
