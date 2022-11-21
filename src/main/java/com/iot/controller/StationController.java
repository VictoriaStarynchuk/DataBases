package com.iot.controller;

import com.iot.domain.StationEntity;
import com.iot.dto.StationEntityDto;
import com.iot.dto.assembler.StationDtoAssembler;
import com.iot.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/stations")
public class StationController {
    @Autowired
    private StationService stationService;

    @Autowired
    private StationDtoAssembler stationDtoAssembler;

    @GetMapping(value = "{stationId}")
    public ResponseEntity<StationEntityDto> getStation(@PathVariable Integer stationId){
        StationEntity stationEntity = stationService.findById(stationId);
        StationEntityDto stationEntityDto = stationDtoAssembler.toModel(stationEntity);
        return new ResponseEntity<>(stationEntityDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<StationEntityDto>> getAllStations(){
        List<StationEntity> stationEntities = stationService.findAll();
        CollectionModel<StationEntityDto> stationEntityDtos= stationDtoAssembler.toCollectionModel(stationEntities );
        return new ResponseEntity<>(stationEntityDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<StationEntityDto> addStation(@RequestBody StationEntity station){
        StationEntity newStation = stationService.create(station);
        StationEntityDto stationEntityDto = stationDtoAssembler.toModel(station);
        return new ResponseEntity<>(stationEntityDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{stationId}")
    public ResponseEntity<?> updateStation(@RequestBody StationEntity uStation, @PathVariable Integer stationId){
        stationService.update(stationId, uStation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{stationId}")
    public ResponseEntity<?> deleteStation(@PathVariable Integer stationId){
        stationService.delete((stationId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
