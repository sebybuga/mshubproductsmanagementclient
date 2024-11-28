package com.hubproductsmanagement.exception;

import com.github.dozermapper.core.MappingException;

public class ProblemMappingDataFromDB extends Throwable {
    public ProblemMappingDataFromDB(String message, MappingException e) {
        super(message,e);
    }
}
