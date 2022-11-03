package com.iot.lab4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Station {
    private Integer id;
    private Float areaSqKm;
    private Integer energyId;
    private Integer elementId;
    private Integer businessLandId;
}
