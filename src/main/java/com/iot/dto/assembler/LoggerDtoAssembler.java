package com.iot.dto.assembler;

import com.iot.controller.LoggerController;
import com.iot.domain.LoggerEntity;
import com.iot.dto.LoggerEntityDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class LoggerDtoAssembler implements RepresentationModelAssembler<LoggerEntity, LoggerEntityDto> {
    @Override
    public LoggerEntityDto toModel(LoggerEntity entity) {
        LoggerEntityDto loggerEntityDto = LoggerEntityDto.builder()
                .id(entity.getId())
                .businessLand(entity.getBusinessLand())
                .owner(entity.getOwner())
                .action(entity.getAction())
                .timeStamp(entity.getTimeStamp())
                .user(entity.getUser())
                .build();
        Link selfLink = linkTo(methodOn(LoggerController.class).getLog(loggerEntityDto.getId())).withSelfRel();
        loggerEntityDto.add(selfLink);
        return loggerEntityDto;

    }

    @Override
    public CollectionModel<LoggerEntityDto> toCollectionModel(Iterable<? extends LoggerEntity> entities) {
        CollectionModel<LoggerEntityDto> loggerEntityDto = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(LoggerController.class).getAllLogs()).withSelfRel();
        loggerEntityDto.add(selfLink);
        return loggerEntityDto;
    }
}
