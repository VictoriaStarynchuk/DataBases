package com.iot.dto.assembler;

import com.iot.controller.CityController;
import com.iot.controller.OwnerController;
import com.iot.domain.OwnerEntity;
import com.iot.dto.CityEntityDto;
import com.iot.dto.OwnerEntityDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OwnerDtoAssembler implements RepresentationModelAssembler<OwnerEntity, OwnerEntityDto> {
    @Override
    public OwnerEntityDto toModel(OwnerEntity entity) {
        OwnerEntityDto ownerEntityDto = OwnerEntityDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .build();
        Link selfLink = linkTo(methodOn(OwnerController.class).getOwner(ownerEntityDto.getId())).withSelfRel();
        ownerEntityDto.add(selfLink);
        return ownerEntityDto;
    }

    @Override
    public CollectionModel<OwnerEntityDto> toCollectionModel(Iterable<? extends OwnerEntity> entities) {
        CollectionModel<OwnerEntityDto> ownerEntityDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(OwnerController.class).getAllOwners()).withSelfRel();
        ownerEntityDtos.add(selfLink);
        return ownerEntityDtos;
    }
}
