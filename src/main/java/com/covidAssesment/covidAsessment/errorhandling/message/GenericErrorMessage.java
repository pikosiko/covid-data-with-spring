package com.covidAssesment.covidAsessment.errorhandling.message;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GenericErrorMessage {

    private LocalDateTime timestamp;
    private String message;
    private int status;
    private String detailedMessage;
}
