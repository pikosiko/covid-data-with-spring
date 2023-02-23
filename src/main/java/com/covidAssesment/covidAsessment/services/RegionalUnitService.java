package com.covidAssesment.covidAsessment.services;

import com.covidAssesment.covidAsessment.errorhandling.exceptions.ItemNotFoundException;
import com.covidAssesment.covidAsessment.models.csv.RegionalUnitCsv;
import com.covidAssesment.covidAsessment.models.db.CovidStatsDb;
import com.covidAssesment.covidAsessment.models.db.RegionalUnitDb;
import com.covidAssesment.covidAsessment.models.dto.CovidStatsDto;
import com.covidAssesment.covidAsessment.models.dto.RegionalUnitDto;
import com.covidAssesment.covidAsessment.repositories.CovidStatsRepository;
import com.covidAssesment.covidAsessment.repositories.RegionalUnitRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class RegionalUnitService {

    private final RegionalUnitRepository regionalUnitRepository;
    private final CovidStatsRepository covidStatsRepository;

    public RegionalUnitService(RegionalUnitRepository regionalUnitRepository, CovidStatsRepository covidStatsRepository){
        this.regionalUnitRepository = regionalUnitRepository;
        this.covidStatsRepository = covidStatsRepository;
    }

    public void populateRegionalUnits() {

        try {

            Reader reader = Files.newBufferedReader(new File("src/main/resources/populationRegionalUnits.csv").toPath());
            CsvToBean<RegionalUnitCsv> cb = new CsvToBeanBuilder<RegionalUnitCsv>(reader)
                    .withType(RegionalUnitCsv.class)
                    .build();
            List<RegionalUnitCsv> cbParse = cb.parse();
            System.out.println(cb);
            System.out.println(cbParse);

            List <RegionalUnitDb> regionalUnitDbList = cbParse.stream().map(csvObject-> new RegionalUnitDb(csvObject.getId(), csvObject.getRegionalUnit(), csvObject.getPopulation())).collect(Collectors.toList());
            System.out.println(regionalUnitDbList);
            regionalUnitDbList.forEach(regionalUnitDb -> regionalUnitRepository.save(regionalUnitDb));

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    public  Map<String, String> getPopulationForRegion(int id){
        Map<String, String> returnedMap = new HashMap<>();

        RegionalUnitDb regionalUnit =regionalUnitRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        returnedMap.put(regionalUnit.getRegionalUnit(), regionalUnit.getPopulation());
        return returnedMap;
    }
    public  List<CovidStatsDto> getDailyVaccinationsForARegion(int id) {

        List<CovidStatsDb> covidStatsDb = covidStatsRepository.findByAreaId(id);
        return covidStatsDb.stream()
                .map(entity -> new CovidStatsDto(entity.getAreaId(), entity.getDayTotal(), entity.getReferenceDate()))
                .toList();
    }

    public List<RegionalUnitDto> getPercentageOfVaccinatedPopulation() {
        List<RegionalUnitDb> regionalUnitDbList = regionalUnitRepository.findAll();
        List<CovidStatsDb> covidStatsDbList = covidStatsRepository.findAll();
        List<RegionalUnitDto> regionalUnitDtoList = new ArrayList<>();

        int maxVaccinations = Integer.MIN_VALUE;
        String strippedTotalPersons = "";

        for (RegionalUnitDb regionalUnitDb : regionalUnitDbList) {
            for (CovidStatsDb covidStatsDb : covidStatsDbList) {
                if (regionalUnitDb.getId() == covidStatsDb.getAreaId()) {
                    strippedTotalPersons = covidStatsDb.getTotalDistinctPersons().replace("\"", "").replace(".", "");
                    if (maxVaccinations < Integer.parseInt(strippedTotalPersons)) {
                        maxVaccinations = Integer.parseInt(strippedTotalPersons);
                    }
                }
            }
            regionalUnitDtoList.add(new RegionalUnitDto(regionalUnitDb.getId(), regionalUnitDb.getRegionalUnit(), regionalUnitDb.getPopulation(), getPercentage(regionalUnitDb.getPopulation(), maxVaccinations)));
            maxVaccinations = Integer.MIN_VALUE;
        }
        return regionalUnitDtoList;
    }

    public Double getPercentage(String population, int maxVaccinations) {
        String strippedPopulation = population.replace("\"", "").replace(".", "");
        double intPopulation = Integer.parseInt(strippedPopulation);
        return ((maxVaccinations/intPopulation)*100);
    }

}
