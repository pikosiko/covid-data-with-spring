package com.covidAssesment.covidAsessment.controllers;

import com.covidAssesment.covidAsessment.models.dto.CovidStatsDto;
import com.covidAssesment.covidAsessment.models.dto.RegionalUnitDto;
import com.covidAssesment.covidAsessment.services.RegionalUnitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AppController {


    private final RegionalUnitService regionalUnitService;

    public AppController(RegionalUnitService regionalUnitService){
        this.regionalUnitService = regionalUnitService;
    }


    @GetMapping("/regionalUnit/{id}")
    Map<String, String> findPopulationById(@PathVariable int id) {
        Map<String, String> regionPopulationMap = regionalUnitService.getPopulationForRegion(id);
        return regionPopulationMap;
    }

    @GetMapping("/regionalUnit/dailyVaccinations/{id}")
    List<CovidStatsDto> findDailyVaccinationsForARegion(@PathVariable int id) {
        List<CovidStatsDto> returnedData = regionalUnitService.getDailyVaccinationsForARegion(id);
        return returnedData;
    }



    @GetMapping("/regionalUnit/percentage")
    List<RegionalUnitDto> findPercentageOfVaccinatedPopulation() {
        List<RegionalUnitDto> regionalUnitDtoList = regionalUnitService.getPercentageOfVaccinatedPopulation();
        return regionalUnitDtoList;
    }

}
