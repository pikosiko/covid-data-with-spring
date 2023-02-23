package com.covidAssesment.covidAsessment.services;


import com.covidAssesment.covidAsessment.models.csv.CovidStatsCsv;
import com.covidAssesment.covidAsessment.models.db.CovidStatsDb;
import com.covidAssesment.covidAsessment.repositories.CovidStatsRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CovidStatsService {

    private final CovidStatsRepository covidStatsRepository;

    public CovidStatsService(CovidStatsRepository covidStatsRepository){
        this.covidStatsRepository = covidStatsRepository;

    }

    public void populateCovidStats() {


        try {

            Reader reader = Files.newBufferedReader(new File("src/main/resources/covidStats.csv").toPath());
            CsvToBean<CovidStatsCsv> cb = new CsvToBeanBuilder<CovidStatsCsv>(reader)
                    .withType(CovidStatsCsv.class)
                    .build();
            List<CovidStatsCsv> cbParse = cb.parse();
            System.out.println(cb);
            System.out.println(cbParse);

            List <CovidStatsDb>  covidStatsDbList = cbParse.stream().map(csvObject-> new CovidStatsDb(
                    csvObject.getArea(), csvObject.getAreaId(), csvObject.getDailyDose1(), csvObject.getDailyDose2(),
                    csvObject.getDayDiff(), csvObject.getDayTotal(), csvObject.getReferenceDate(),
                    csvObject.getTotalDistinctPersons(), csvObject.getTotalDose1(), csvObject.getTotalDose2(),
                    csvObject.getTotalVaccinations())).collect(Collectors.toList());
            System.out.println(covidStatsDbList);
            covidStatsDbList.forEach(covidStatDb -> covidStatsRepository.save(covidStatDb));

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

}