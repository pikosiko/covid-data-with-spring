package com.covidAssesment.covidAsessment.models.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RegionalUnitCsv {


    @CsvBindByName(column = "Id")
    private int id;

    @CsvBindByName(column = "Regional Unit")
    private String regionalUnit;

    @CsvBindByName(column = "Population")
    private String population;

}
