package com.covidAssesment.covidAsessment.models.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CovidStatsCsv {

    @CsvBindByName(column = "area")
    private String area;

    @CsvBindByName(column = "areaid")
    private int areaId;

    @CsvBindByName(column = "dailydose1")
    private String dailyDose1;

    @CsvBindByName(column = "dailydose2")
    private String dailyDose2;

    @CsvBindByName(column = "daydiff")
    private String dayDiff;

    @CsvBindByName(column = "daytotal")
    private String dayTotal;

    @CsvBindByName(column = "referencedate")
    private String referenceDate;

    @CsvBindByName(column = "totaldistinctpersons")
    private String totalDistinctPersons;

    @CsvBindByName(column = "totaldose1")
    private String totalDose1;

    @CsvBindByName(column = "totaldose2")
    private String totalDose2;

    @CsvBindByName(column = "totalvaccinations")
    private String totalVaccinations;


}
