package com.iot.dto.assembler;

import com.iot.controller.EnergyController;
import com.iot.controller.EnergyMarketController;
import com.iot.domain.EnergyMarketEntity;
import com.iot.dto.EnergyEntityDto;
import com.iot.dto.EnergyMarketEntityDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EnergyMarketDtoAssembler implements RepresentationModelAssembler<EnergyMarketEntity, EnergyMarketEntityDto> {
    @Override
    public EnergyMarketEntityDto toModel(EnergyMarketEntity entity) {
        EnergyMarketEntityDto energyMarketEntityDto = EnergyMarketEntityDto.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .price(entity.getPrice())
                .time(entity.getTime())
                .build();
        Link selfLink = linkTo(methodOn(EnergyMarketController.class).getEnergyMarket(energyMarketEntityDto.getId())).withSelfRel();
        energyMarketEntityDto.add(selfLink);
        return energyMarketEntityDto;
    }

    @Override
    public CollectionModel<EnergyMarketEntityDto> toCollectionModel(Iterable<? extends EnergyMarketEntity> entities) {
        CollectionModel<EnergyMarketEntityDto> energyMarketEntityDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(EnergyMarketController.class).getAllEnergyMarkets()).withSelfRel();
        energyMarketEntityDtos.add(selfLink);
        return energyMarketEntityDtos;
    }
}
