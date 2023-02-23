package com.covidAssesment.covidAsessment.repositories;

import com.covidAssesment.covidAsessment.models.db.CovidStatsDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CovidStatsRepository extends JpaRepository<CovidStatsDb, Integer> {

    List<CovidStatsDb> findByAreaId(int id);


}
