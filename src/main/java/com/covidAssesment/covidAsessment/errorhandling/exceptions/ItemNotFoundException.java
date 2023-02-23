package com.covidAssesment.covidAsessment.errorhandling.exceptions;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(int id) {
        super("Could not find regional unit with id: " + id);
    }
}
