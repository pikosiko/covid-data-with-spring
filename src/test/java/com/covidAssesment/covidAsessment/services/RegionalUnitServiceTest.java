package com.covidAssesment.covidAsessment.services;

import com.covidAssesment.covidAsessment.models.db.CovidStatsDb;
import com.covidAssesment.covidAsessment.models.db.RegionalUnitDb;
import com.covidAssesment.covidAsessment.models.dto.RegionalUnitDto;
import com.covidAssesment.covidAsessment.repositories.CovidStatsRepository;
import com.covidAssesment.covidAsessment.repositories.RegionalUnitRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class RegionalUnitServiceTest {

    @MockBean
    RegionalUnitRepository regionalUnitRepository;
    @MockBean
    CovidStatsRepository covidStatsRepository;

    @InjectMocks
    @Resource
    RegionalUnitService regionalUnitService;

    @Test
    void testGetPercentage() {
        String population = "1.029.520";
        int maxVaccinations = 290680;
        Double actualPercentage = regionalUnitService.getPercentage(population, maxVaccinations);
        Double expectedPercentage = 28.234517056492347;
        assertEquals(expectedPercentage, actualPercentage);
    }

    @Test
    void testGetPercentageOfVaccinatedPopulation() {

        List<RegionalUnitDto> expectedRegionalUnitDtoList = Arrays.asList(new RegionalUnitDto(202,"Π.Ε. Θεσσαλονίκης","1.110.312", 24.653610876942697),
                                                                            new RegionalUnitDto(905,"Π.Ε. Κεντρικού Τομέα Αθηνών","1.029.520", 28.234517056492347),
                                                                            new RegionalUnitDto(902,"Π.Ε. Βορείου Τομέα Αθηνών","591.680", 41.34329367225527));

        List<RegionalUnitDb> regionalUnitDbList = Arrays.asList(new RegionalUnitDb(202,"Π.Ε. Θεσσαλονίκης","1.110.312"),
                                                                new RegionalUnitDb(905,"Π.Ε. Κεντρικού Τομέα Αθηνών","1.029.520"),
                                                                new RegionalUnitDb(902,"Π.Ε. Βορείου Τομέα Αθηνών","591.680"));
        List<CovidStatsDb> covidStatsDbList = Arrays.asList(new CovidStatsDb("ΘΕΣΣΑΛΟΝΙΚΗΣ",202,"5747","7107","159","12504","2021-05-06T00:00:00","273732","273735","125770","398806"),
                                                            new CovidStatsDb("ΚΕΝΤΡΙΚΟΥ ΤΟΜΕΑ ΑΘΗΝΩΝ",905,"3986","7112","207","10539","2021-05-06T00:00:00","290680","290682","151352","440941"),
                                                            new CovidStatsDb("ΒΟΡΕΙΟΥ ΤΟΜΕΑ ΑΘΗΝΩΝ",902,"6916","2489","-654","9210","2021-05-07T00:00:00","244620","244621","134320","378359"));

        when(regionalUnitRepository.findAll()).thenReturn(regionalUnitDbList);
        when(covidStatsRepository.findAll()).thenReturn(covidStatsDbList);

        List<RegionalUnitDto> regionalUnitDtoList = regionalUnitService.getPercentageOfVaccinatedPopulation();

        assertEquals(expectedRegionalUnitDtoList, regionalUnitDtoList);


    }
}