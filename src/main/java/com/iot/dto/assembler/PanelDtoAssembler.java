package com.iot.dto.assembler;

import com.iot.controller.CityController;
import com.iot.controller.PanelController;
import com.iot.domain.PanelEntity;
import com.iot.dto.CityEntityDto;
import com.iot.dto.PanelEntityDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PanelDtoAssembler implements RepresentationModelAssembler<PanelEntity, PanelEntityDto> {
    @Override
    public PanelEntityDto toModel(PanelEntity entity) {
        PanelEntityDto panelEntityDto = PanelEntityDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .power(entity.getPower())
                .durationTime(entity.getDurationTime())
                .productionPower(entity.getProductionPower())
                .tiltAngel(entity.getTiltAngel())
                .build();
        Link selfLink = linkTo(methodOn(PanelController.class).getPanel(panelEntityDto.getId())).withSelfRel();
        panelEntityDto.add(selfLink);
        return panelEntityDto;

    }

    @Override
    public CollectionModel<PanelEntityDto> toCollectionModel(Iterable<? extends PanelEntity> entities) {
        CollectionModel<PanelEntityDto> panelEntityDtos= RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(PanelController.class).getAllPanels()).withSelfRel();
        panelEntityDtos.add(selfLink);
        return panelEntityDtos;
    }
}
