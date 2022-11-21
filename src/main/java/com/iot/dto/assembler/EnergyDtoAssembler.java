package com.iot.dto.assembler;

import com.iot.controller.BatteryController;
import com.iot.controller.EnergyController;
import com.iot.domain.EnergyEntity;
import com.iot.dto.EnergyEntityDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EnergyDtoAssembler implements RepresentationModelAssembler<EnergyEntity, EnergyEntityDto> {
    @Override
    public EnergyEntityDto toModel(EnergyEntity entity) {
        EnergyEntityDto energyEntityDto = EnergyEntityDto.builder()
                .id(entity.getId())
                .solarAmount(entity.getSolarAmount())
                .exporting(entity.getExporting())
                .useNow(entity.getUseNow())
                .build();
        Link selfLink = linkTo(methodOn(EnergyController.class).getEnergy(energyEntityDto.getId())).withSelfRel();
        energyEntityDto.add(selfLink);
        return energyEntityDto;
    }

    @Override
    public CollectionModel<EnergyEntityDto> toCollectionModel(Iterable<? extends EnergyEntity> entities) {
        CollectionModel<EnergyEntityDto> energyEntityDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(EnergyController.class).getAllEnergy()).withSelfRel();
        energyEntityDtos.add(selfLink);
        return energyEntityDtos;
    }
}
