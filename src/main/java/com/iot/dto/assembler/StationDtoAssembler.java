package com.iot.dto.assembler;

import com.iot.controller.CityController;
import com.iot.controller.StationController;
import com.iot.domain.StationEntity;
import com.iot.dto.CityEntityDto;
import com.iot.dto.StationEntityDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StationDtoAssembler implements RepresentationModelAssembler<StationEntity, StationEntityDto> {
    @Override
    public StationEntityDto toModel(StationEntity entity) {
        StationEntityDto stationEntityDto = StationEntityDto.builder()
                .id(entity.getId())
                .areaSqKm(entity.getAreaSqKm())
                .build();
        Link selfLink = linkTo(methodOn(StationController.class).getStation(stationEntityDto.getId())).withSelfRel();
        stationEntityDto.add(selfLink);
        return stationEntityDto;
    }

    @Override
    public CollectionModel<StationEntityDto> toCollectionModel(Iterable<? extends StationEntity> entities) {
        CollectionModel<StationEntityDto> stationEntityDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(StationController.class).getAllStations()).withSelfRel();
        stationEntityDtos.add(selfLink);
        return stationEntityDtos;
    }
}
