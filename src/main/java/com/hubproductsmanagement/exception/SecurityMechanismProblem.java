package com.hubproductsmanagement.exception;

public class SecurityMechanismProblem extends RuntimeException {
    public SecurityMechanismProblem(String message, Throwable e) {
        super(message, e);
    }
}