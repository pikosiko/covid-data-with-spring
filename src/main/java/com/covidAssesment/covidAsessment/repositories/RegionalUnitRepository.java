package com.covidAssesment.covidAsessment.repositories;

import com.covidAssesment.covidAsessment.models.db.RegionalUnitDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionalUnitRepository extends JpaRepository<RegionalUnitDb, Integer> {

}
