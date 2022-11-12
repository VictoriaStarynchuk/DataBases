package com.iot.controller;

import com.iot.domain.OwnerEntity;
import com.iot.dto.OwnerEntityDto;
import com.iot.dto.assembler.OwnerDtoAssembler;
import com.iot.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/owners")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @Autowired
    private OwnerDtoAssembler ownerDtoAssembler;

    @GetMapping(value = "{ownerId}")
    public ResponseEntity<OwnerEntityDto> getOwner(@PathVariable Integer ownerId){
        OwnerEntity ownerEntity = ownerService.findById(ownerId);
        OwnerEntityDto ownerEntityDto = ownerDtoAssembler.toModel(ownerEntity);
        return new ResponseEntity<>(ownerEntityDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<OwnerEntityDto>> getAllOwners(){
        List<OwnerEntity> ownerEntities = ownerService.findAll();
        CollectionModel<OwnerEntityDto> ownerEntityDtos= ownerDtoAssembler.toCollectionModel(ownerEntities);
        return new ResponseEntity<>(ownerEntityDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<OwnerEntityDto> addOwner(@RequestBody OwnerEntity owner){
        OwnerEntity newOwner = ownerService.create(owner);
        OwnerEntityDto ownerEntityDto = ownerDtoAssembler.toModel(owner);
        return new ResponseEntity<>(ownerEntityDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{ownerId}")
    public ResponseEntity<?> updateOwner(@RequestBody OwnerEntity uOwner, @PathVariable Integer ownerId){
        ownerService.update(ownerId, uOwner);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{ownerId}")
    public ResponseEntity<?> deleteOwner(@PathVariable Integer ownerId){
        ownerService.delete((ownerId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
