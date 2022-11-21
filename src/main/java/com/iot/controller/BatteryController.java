package com.iot.controller;

import com.iot.domain.BatteryEntity;
import com.iot.dto.BatteryEntityDto;
import com.iot.dto.assembler.BatteryDtoAssembler;
import com.iot.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/batteries")
public class BatteryController {
    @Autowired
    private BatteryService batteryService;

    @Autowired
    private BatteryDtoAssembler batteryDtoAssembler;

    @GetMapping(value = "/{batteryId}")
    public ResponseEntity<BatteryEntityDto> getBattery(@PathVariable Integer batteryId){
        BatteryEntity batteryEntity = batteryService.findById(batteryId);
        BatteryEntityDto batteryEntityDto = batteryDtoAssembler.toModel(batteryEntity);
        return new ResponseEntity<>(batteryEntityDto, HttpStatus.OK);
    }
    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<BatteryEntityDto>> getAllBatteries(){
        List<BatteryEntity> batteryEntityList = batteryService.findAll();
        CollectionModel<BatteryEntityDto> batteryEntityDtos = batteryDtoAssembler.toCollectionModel(batteryEntityList);
        return  new ResponseEntity<>(batteryEntityDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<BatteryEntityDto> addBattery(@RequestBody BatteryEntity batteryEntity){
        BatteryEntity newBattery = batteryService.create(batteryEntity);
        BatteryEntityDto batteryEntityDto = batteryDtoAssembler.toModel(newBattery);
        return new ResponseEntity<>(batteryEntityDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{batteryId}")
    public ResponseEntity<?> updateBattery(@RequestBody BatteryEntity uBattery, @PathVariable Integer batteryId ){
        batteryService.update(batteryId, uBattery);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{batteryId}")
    public ResponseEntity<?> deleteBattery(@PathVariable Integer batteryId){
        batteryService.delete(batteryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

