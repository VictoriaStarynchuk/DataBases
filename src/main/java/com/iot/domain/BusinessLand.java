package com.iot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusinessLand {
    private Integer id;
    private String address;
    private Integer quantityStation;
    private Integer cityId;
}
