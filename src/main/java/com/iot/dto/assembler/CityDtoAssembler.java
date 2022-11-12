package com.iot.dto.assembler;

import com.iot.controller.CityController;
import com.iot.domain.CityEntity;
import com.iot.dto.CityEntityDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CityDtoAssembler implements RepresentationModelAssembler<CityEntity, CityEntityDto> {
    @Override
    public CityEntityDto toModel(CityEntity entity) {
        CityEntityDto cityEntityDto = CityEntityDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .city(entity.getCity())
                .build();
        Link selfLink = linkTo(methodOn(CityController.class).getCity(cityEntityDto.getId())).withSelfRel();
        cityEntityDto.add(selfLink);
        return cityEntityDto;
    }

    @Override
    public CollectionModel<CityEntityDto> toCollectionModel(Iterable<? extends CityEntity> entities) {
        CollectionModel<CityEntityDto> cityEntityDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CityController.class).getAllCities()).withSelfRel();
        cityEntityDtos.add(selfLink);
        return cityEntityDtos;
    }
}
