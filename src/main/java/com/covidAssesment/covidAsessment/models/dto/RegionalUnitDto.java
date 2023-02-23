package com.covidAssesment.covidAsessment.models.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RegionalUnitDto {

    private int id;
    private String regionalUnit;
    private String population;
    private Double percentage;


}
