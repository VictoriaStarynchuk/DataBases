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
@Relation(itemRelation = "element", collectionRelation = "elements")
public class ElementEntityDto extends RepresentationModel<ElementEntityDto> {
    private final Integer id;
    private final Integer panelQuantity;
    private final Integer batteryQuantity;
}