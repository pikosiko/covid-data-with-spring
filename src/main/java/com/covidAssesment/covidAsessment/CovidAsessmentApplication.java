package com.covidAssesment.covidAsessment;

import com.covidAssesment.covidAsessment.services.CovidStatsService;
import com.covidAssesment.covidAsessment.services.RegionalUnitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CovidAsessmentApplication {



	public static void main(String[] args) {


		SpringApplication.run(CovidAsessmentApplication.class, args);

	}
	@Bean
	CommandLineRunner commandLineRunner(RegionalUnitService regionalUnitService){
		return args -> regionalUnitService.populateRegionalUnits();
	}

	@Bean
	CommandLineRunner commandLineRunner2(CovidStatsService covidStatsService){
		return args -> covidStatsService.populateCovidStats();
	}
}
