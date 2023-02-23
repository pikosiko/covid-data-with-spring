package com.covidAssesment.covidAsessment.errorhandling.message;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class RegionalUnitNotFoundExceptionMessage {

    private LocalDateTime timestamp;
    private String message;
    private int status;

}
