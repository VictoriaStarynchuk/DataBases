package com.iot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "panel", collectionRelation = "panels")
public class PanelEntityDto extends RepresentationModel<PanelEntityDto> {
    private final Integer id;
    private final String type;
    private final Integer power;
    private final Integer durationTime;
    private final String tiltAngel;
    private final Integer productionPower;
}