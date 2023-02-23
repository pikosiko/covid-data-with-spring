package com.covidAssesment.covidAsessment.models.db;


import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "populationalregionalunits")
public class RegionalUnitDb {

    @Id
    private int id;
    private String regionalUnit;
    private String population;
}
