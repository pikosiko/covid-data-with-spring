package com.covidAssesment.covidAsessment.models.db;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "covidstats")
public class CovidStatsDb {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String area;

    public CovidStatsDb(String area, int areaId, String dailyDose1, String dailyDose2, String dayDiff, String dayTotal, String referenceDate, String totalDistinctPersons, String totalDose1, String totalDose2, String totalVaccinations) {
        this.area = area;
        this.areaId = areaId;
        this.dailyDose1 = dailyDose1;
        this.dailyDose2 = dailyDose2;
        this.dayDiff = dayDiff;
        this.dayTotal = dayTotal;
        this.referenceDate = referenceDate;
        this.totalDistinctPersons = totalDistinctPersons;
        this.totalDose1 = totalDose1;
        this.totalDose2 = totalDose2;
        this.totalVaccinations = totalVaccinations;
    }

    private int areaId;
    private String dailyDose1;
    private String dailyDose2;
    private String dayDiff;
    private String dayTotal;
    private String referenceDate;
    private String totalDistinctPersons;
    private String totalDose1;
    private String totalDose2;
    private String totalVaccinations;
}
