package com.covidAssesment.covidAsessment.models.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CovidStatsDto {
    private int areaId;
    private String dayTotal;
    private String referenceDate;
}
