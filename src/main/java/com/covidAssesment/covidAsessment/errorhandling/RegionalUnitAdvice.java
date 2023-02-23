package com.covidAssesment.covidAsessment.errorhandling;

import com.covidAssesment.covidAsessment.errorhandling.exceptions.ItemNotFoundException;
import com.covidAssesment.covidAsessment.errorhandling.message.GenericErrorMessage;
import com.covidAssesment.covidAsessment.errorhandling.message.RegionalUnitNotFoundExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RegionalUnitAdvice extends ResponseEntityExceptionHandler {

        @ResponseBody
        @ExceptionHandler(ItemNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        ResponseEntity<RegionalUnitNotFoundExceptionMessage> regionNotFoundHandler(ItemNotFoundException ex) {
            HttpStatus httpStatus = HttpStatus.NOT_FOUND;
            RegionalUnitNotFoundExceptionMessage message = RegionalUnitNotFoundExceptionMessage.builder()
                    .status(httpStatus.value())
                    .timestamp(LocalDateTime.now())
                    .message(ex.getMessage()).build();

            return ResponseEntity.status(httpStatus).body(message);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<GenericErrorMessage> genericErrorHandler(Exception ex) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        GenericErrorMessage message = GenericErrorMessage.builder()
                .status(httpStatus.value())
                .timestamp(LocalDateTime.now())
                .message("Internal Error Occurred")
                .detailedMessage(ex.getMessage()).build();

        return ResponseEntity.status(httpStatus).body(message);
    }
}
