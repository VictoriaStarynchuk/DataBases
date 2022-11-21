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
@Relation(itemRelation = "energy", collectionRelation = "someEnergy")
public class EnergyEntityDto extends RepresentationModel<ElementEntityDto> {
    private final Integer id;
    private final Double solarAmount;
    private final Double useNow;
    private final Double exporting;
}