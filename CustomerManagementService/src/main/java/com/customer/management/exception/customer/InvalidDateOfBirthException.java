package com.customer.management.exception.customer;

public class InvalidDateOfBirthException extends RuntimeException {

    public InvalidDateOfBirthException(String errMessage) {
        super(errMessage);
    }
}
