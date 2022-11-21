package com.iot.dto.assembler;

import com.iot.controller.CountryController;
import com.iot.domain.CountryEntity;
import com.iot.dto.CountryEntityDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CountryDtoAssembler implements RepresentationModelAssembler<CountryEntity, CountryEntityDto> {
    @Override
    public CountryEntityDto toModel(CountryEntity entity) {
        CountryEntityDto countryEntityDto = CountryEntityDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(CountryController.class).getCountry(countryEntityDto.getId())).withSelfRel();
        countryEntityDto.add(selfLink);
        return countryEntityDto;
    }

    @Override
    public CollectionModel<CountryEntityDto> toCollectionModel(Iterable<? extends CountryEntity> entities) {
        CollectionModel<CountryEntityDto> countryEntityDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CountryController.class).getAllCountries()).withSelfRel();
        countryEntityDtos.add(selfLink);
        return countryEntityDtos;
    }
}
