package com.iot.controller;

import com.iot.domain.CityEntity;
import com.iot.dto.CityEntityDto;
import com.iot.dto.assembler.CityDtoAssembler;
import com.iot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/api/cities")
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CityDtoAssembler cityDtoAssembler;

    @GetMapping(value = "{cityId}")
    public ResponseEntity<CityEntityDto> getCity(@PathVariable Integer cityId){
        CityEntity cityEntity = cityService.findById(cityId);
        CityEntityDto cityEntityDto = cityDtoAssembler.toModel(cityEntity);
        return new ResponseEntity<>(cityEntityDto, HttpStatus.OK);
    }
    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CityEntityDto>> getAllCities(){
           List<CityEntity> cityEntities = cityService.findAll();
           CollectionModel<CityEntityDto> cityEntityDtos = cityDtoAssembler.toCollectionModel(cityEntities);
           return new ResponseEntity<>(cityEntityDtos, HttpStatus.OK);
       }

    @PostMapping(value = "")
    public ResponseEntity<CityEntityDto> addCity(@RequestBody CityEntity cityEntity){
        CityEntity newCity = cityService.create(cityEntity);
        CityEntityDto cityEntityDto = cityDtoAssembler.toModel(cityEntity);
        return new ResponseEntity<>(cityEntityDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{cityId}")
    public ResponseEntity<?> updateCity(@RequestBody CityEntity uCity, @PathVariable Integer cityId){
        cityService.update(cityId, uCity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{cityId}")
    public ResponseEntity<?> deleteCity(@PathVariable Integer cityId){
        cityService.delete((cityId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
