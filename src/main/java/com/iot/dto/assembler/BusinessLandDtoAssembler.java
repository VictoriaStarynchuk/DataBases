package com.iot.dto.assembler;

import com.iot.controller.BusinessLandController;
import com.iot.domain.BusinessLandEntity;
import com.iot.dto.BusinessLandEntityDto;
import org.springframework.hateoas.CollectionModel;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BusinessLandDtoAssembler implements RepresentationModelAssembler<BusinessLandEntity, BusinessLandEntityDto> {
    @Override
    public BusinessLandEntityDto toModel(BusinessLandEntity entity) {
        BusinessLandEntityDto businessLandEntityDto = BusinessLandEntityDto.builder()
                .id(entity.getId())
                .quantityStation(entity.getQuantityStation())
                .address(entity.getAddress())
                .build();
        Link selfLink = linkTo(methodOn(BusinessLandController.class).getBusinessLand(businessLandEntityDto.getId())).withSelfRel();
        businessLandEntityDto.add(selfLink);
        return businessLandEntityDto;
    }

    @Override
    public CollectionModel<BusinessLandEntityDto> toCollectionModel(Iterable<? extends BusinessLandEntity> entities) {
        CollectionModel<BusinessLandEntityDto> businessLandEntityDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(BusinessLandController.class).getAllBusinessLands()).withSelfRel();
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
