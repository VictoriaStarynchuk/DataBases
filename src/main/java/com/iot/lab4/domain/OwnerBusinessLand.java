package com.iot.lab4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OwnerBusinessLand {
    private Integer ownerId;
    private Integer businessLandId;
    private Integer quantityLand;
}
