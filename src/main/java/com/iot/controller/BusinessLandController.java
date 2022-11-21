package com.iot.controller;

import com.iot.domain.BusinessLandEntity;
import com.iot.dto.BusinessLandEntityDto;
import com.iot.dto.assembler.BusinessLandDtoAssembler;
import com.iot.service.BusinessLandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/businessLands")
public class BusinessLandController {
    @Autowired
    private BusinessLandService businessLandService;
    @Autowired
    private BusinessLandDtoAssembler businessLandDtoAssembler;

    @GetMapping(value = "/{businessLandId}")
    public ResponseEntity<BusinessLandEntityDto> getBusinessLand(@PathVariable Integer businessLandId) {
        BusinessLandEntity businessLandEntity = businessLandService.findById(businessLandId);
        BusinessLandEntityDto businessLandDto = businessLandDtoAssembler.toModel(businessLandEntity);
        return new ResponseEntity<>(businessLandDto, HttpStatus.OK);
    }

   @GetMapping(value = "")
   public ResponseEntity<CollectionModel<BusinessLandEntityDto>> getAllBusinessLands() {
       List<BusinessLandEntity> businessLandEntities = businessLandService.findAll();
       CollectionModel<BusinessLandEntityDto> businessLandEntityDtos = businessLandDtoAssembler.toCollectionModel(businessLandEntities);
       return new ResponseEntity<>(businessLandEntityDtos, HttpStatus.OK);
   }

    @PostMapping(value = "")
    public ResponseEntity<BusinessLandEntityDto> addBusinessLand(@RequestBody BusinessLandEntity businessLand) {
        BusinessLandEntity newBusinessLand= businessLandService.create(businessLand);
        BusinessLandEntityDto businessLandEntityDto = businessLandDtoAssembler.toModel(businessLand);
        return new ResponseEntity<>(businessLandEntityDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{businessLandId}")
    public ResponseEntity<?> updateBook(@RequestBody BusinessLandEntity uBusinessLand, @PathVariable Integer businessLandId) {
        businessLandService.update(businessLandId, uBusinessLand);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{businessLandId}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer businessLandId) {
        businessLandService.delete(businessLandId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}