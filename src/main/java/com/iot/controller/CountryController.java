package com.iot.controller;

import com.iot.domain.CountryEntity;
import com.iot.dto.CountryEntityDto;
import com.iot.dto.assembler.CountryDtoAssembler;
import com.iot.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private CountryDtoAssembler countryDtoAssembler;

    @GetMapping(value = "{countryId}")
    public ResponseEntity<CountryEntityDto> getCountry(@PathVariable Integer countryId){
        CountryEntity countryEntity = countryService.findById(countryId);
        CountryEntityDto countryEntityDto = countryDtoAssembler.toModel(countryEntity);
        return new ResponseEntity<>(countryEntityDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CountryEntityDto>> getAllCountries(){
        List<CountryEntity> countryEntities = countryService.findAll();
        CollectionModel<CountryEntityDto> countryEntityDtos = countryDtoAssembler.toCollectionModel(countryEntities);
        return new ResponseEntity<>(countryEntityDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CountryEntityDto> addCountry(@RequestBody CountryEntity countryEntity){
        CountryEntity newCountry = countryService.create(countryEntity);
        CountryEntityDto countryEntityDto = countryDtoAssembler.toModel(countryEntity);
        return new ResponseEntity<>(countryEntityDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{countryId}")
    public ResponseEntity<?> updateCountry(@RequestBody CountryEntity uCountry, @PathVariable Integer countryId){
        countryService.update(countryId, uCountry);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "/{countryId}")
    public ResponseEntity<?> deleteCountry(@PathVariable Integer countryId){
        countryService.delete((countryId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
