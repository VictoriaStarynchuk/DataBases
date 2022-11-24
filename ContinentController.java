package com.iot.controller;

import com.iot.domain.ContinentEntity;
import com.iot.dto.ContinentEntityDto;
import com.iot.dto.assembler.ContinentDtoAssembler;
import com.iot.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/continents")
public class ContinentController {
    @Autowired
    private ContinentService continentService;
    @Autowired
    private ContinentDtoAssembler continentDtoAssembler;

    @GetMapping(value = "{continentId}")
    public ResponseEntity<ContinentEntityDto> getContinent(@PathVariable Integer continentId){
        ContinentEntity continentEntity = continentService.findById(continentId);
        ContinentEntityDto continentEntityDto = continentDtoAssembler.toModel(continentEntity);
        return new ResponseEntity<>(continentEntityDto, HttpStatus.OK);
    }
    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ContinentEntityDto>> getAllContinents(){
        List<ContinentEntity> continentEntities = continentService.findAll();
        CollectionModel<ContinentEntityDto> continentEntityDtos = continentDtoAssembler.toCollectionModel(continentEntities);
        return new ResponseEntity<>(continentEntityDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ContinentEntityDto> addContinent(@RequestBody ContinentEntity continentEntity){
        ContinentEntity newContinent = continentService.create(continentEntity);
        ContinentEntityDto continentEntityDto = continentDtoAssembler.toModel(continentEntity);
        return new ResponseEntity<>(continentEntityDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{continentId}")
    public ResponseEntity<?> updateContinent(@RequestBody ContinentEntity uContinent, @PathVariable Integer continentId){
        continentService.update(continentId, uContinent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{continentId}")
    public ResponseEntity<?> deleteContinent(@PathVariable Integer continentId){
        continentService.delete((continentId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/parametrized")
    public ResponseEntity<ContinentEntityDto> addParametrizedContinent(@RequestBody ContinentEntityDto continentEntityDto){
        ContinentEntityDto continentEntityDto1 = continentService.insertNewValues(continentEntityDto);
        return new ResponseEntity<>(continentEntityDto1,HttpStatus.OK);
    }

    @PostMapping(value = "/insert_rows")
    public ResponseEntity<?> insertRows(){
        continentService.insertRows();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/create_tables_using_cursor")
    public ResponseEntity<?> createTablesWithCursor(){
        continentService.createTablesWithCursor();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
