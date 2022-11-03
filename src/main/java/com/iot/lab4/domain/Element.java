package com.iot.lab4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Element {
    private Integer id;
    private Integer panelQuantity;
    private Integer batteryQuantity;
    private Integer panelId;
    private Integer batteryId;
}
