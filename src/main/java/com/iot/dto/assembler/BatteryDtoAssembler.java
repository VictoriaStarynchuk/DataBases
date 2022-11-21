package com.iot.dto.assembler;

import com.iot.controller.BatteryController;
import com.iot.domain.BatteryEntity;
import com.iot.dto.BatteryEntityDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BatteryDtoAssembler implements RepresentationModelAssembler<BatteryEntity, BatteryEntityDto> {
    @Override
    public BatteryEntityDto toModel(BatteryEntity entity) {
        BatteryEntityDto batteryEntityDto = BatteryEntityDto.builder()
                .id(entity.getId())
                .capacity(entity.getCapacity())
                .durationTime(entity.getDurationTime())
                .power(entity.getPower())
                .chargeLevel(entity.getChargeLevel())
                .type((entity.getType()))
                .build();
        Link selfLink = linkTo(methodOn(BatteryController.class).getBattery(batteryEntityDto.getId())).withSelfRel();
        batteryEntityDto.add(selfLink);
        return batteryEntityDto;
    }


    @Override
    public CollectionModel<BatteryEntityDto> toCollectionModel(Iterable<? extends BatteryEntity> entities) {
        CollectionModel<BatteryEntityDto> batteryEntityDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(BatteryController.class).getAllBatteries()).withSelfRel();
        batteryEntityDtos.add(selfLink);
        return batteryEntityDtos;
    }

//    public CollectionModel<BatteryEntityDto> toCollectionModel(Iterable<? extends BatteryEntity> entities, Link link) {
//        CollectionModel<BatteryEntityDto> batteryEntityDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
//        batteryEntityDtos.add(link);
//        return batteryEntityDtos;
//    }
}
