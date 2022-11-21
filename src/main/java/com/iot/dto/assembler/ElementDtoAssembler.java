package com.iot.dto.assembler;

import com.iot.controller.BatteryController;
import com.iot.controller.ElementController;
import com.iot.domain.ElementEntity;
import com.iot.dto.BatteryEntityDto;
import com.iot.dto.ElementEntityDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ElementDtoAssembler implements RepresentationModelAssembler<ElementEntity, ElementEntityDto> {
    @Override
    public ElementEntityDto toModel(ElementEntity entity) {
        ElementEntityDto elementEntityDto = ElementEntityDto.builder()
                .id(entity.getId())
                .batteryQuantity(entity.getBatteryQuantity())
                .panelQuantity(entity.getPanelQuantity())
                .build();
        Link selfLink = linkTo(methodOn(ElementController.class).getElement(elementEntityDto.getId())).withSelfRel();
        elementEntityDto.add(selfLink);
        return elementEntityDto;
    }

    @Override
    public CollectionModel<ElementEntityDto> toCollectionModel(Iterable<? extends ElementEntity> entities) {
        CollectionModel<ElementEntityDto> elementEntityDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ElementController.class).getAllElements()).withSelfRel();
        elementEntityDtos.add(selfLink);
        return elementEntityDtos;
    }
}
