package com.iot.controller;

import com.iot.domain.PanelEntity;
import com.iot.dto.PanelEntityDto;
import com.iot.dto.assembler.PanelDtoAssembler;
import com.iot.service.PanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/panels")
public class PanelController {
    @Autowired
    private PanelService panelService;

    @Autowired
    private PanelDtoAssembler panelDtoAssembler;

    @GetMapping(value = "{panelId}")
    public ResponseEntity<PanelEntityDto> getPanel(@PathVariable Integer panelId){
        PanelEntity panelEntity = panelService.findById(panelId);
        PanelEntityDto panelEntityDto = panelDtoAssembler.toModel(panelEntity);
        return new ResponseEntity<>(panelEntityDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<PanelEntityDto>> getAllPanels(){
        List<PanelEntity> panelEntities = panelService.findAll();
        CollectionModel<PanelEntityDto> panelEntityDtos = panelDtoAssembler.toCollectionModel(panelEntities);
        return new ResponseEntity<>(panelEntityDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<PanelEntityDto> addPanel(@RequestBody PanelEntity panel){
        PanelEntity newPanel = panelService.create(panel);
        PanelEntityDto panelEntityDto = panelDtoAssembler.toModel(panel);
        return new ResponseEntity<>(panelEntityDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{panelId}")
    public ResponseEntity<?> updateOwner(@RequestBody PanelEntity uPanel, @PathVariable Integer panelId){
        panelService.update(panelId, uPanel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{ownerId}")
    public ResponseEntity<?> deleteOwner(@PathVariable Integer ownerId){
        panelService.delete((ownerId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
