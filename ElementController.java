package com.iot.controller;

import com.iot.domain.ElementEntity;
import com.iot.dto.ElementEntityDto;
import com.iot.dto.assembler.ElementDtoAssembler;
import com.iot.service.ElementService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/elements")
public class ElementController {
    @Autowired
    private ElementService elementService;
    @Autowired
    private ElementDtoAssembler elementDtoAssembler;

    @GetMapping(value = "{elementId}")
    public ResponseEntity<ElementEntityDto> getElement(@PathVariable Integer elementId){
        ElementEntity elementEntity = elementService.findById(elementId);
        ElementEntityDto elementEntityDto = elementDtoAssembler.toModel(elementEntity);
        return new ResponseEntity<>(elementEntityDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ElementEntityDto>> getAllElements(){
        List<ElementEntity> elementEntities = elementService.findAll();
        CollectionModel<ElementEntityDto> elementEntityDtos = elementDtoAssembler.toCollectionModel(elementEntities);
        return new ResponseEntity<>(elementEntityDtos, HttpStatus.OK);
    }
    @PostMapping(value = "")
    public ResponseEntity<ElementEntityDto> addElement(@RequestBody ElementEntity element){
        ElementEntity newElement = elementService.create(element);
        ElementEntityDto elementEntityDto = elementDtoAssembler.toModel(element);
        return new ResponseEntity<>(elementEntityDto , HttpStatus.CREATED);
    }

    @PutMapping(value = "/{elementId}")
    public ResponseEntity<?> updateElement(@RequestBody ElementEntity uElement, @PathVariable Integer elementId){
        elementService.update(elementId,  uElement);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "/{elementId}")
    public ResponseEntity<?> deleteElement(@PathVariable Integer elementId){
        elementService.delete((elementId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/create_mm_relationship")
    public ResponseEntity<?> manyToManyRel(@RequestBody JSONObject jsonObject){
        elementService.manyToManyRel(jsonObject.getAsString("panel_type"), jsonObject.getAsString("battery_type"));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
