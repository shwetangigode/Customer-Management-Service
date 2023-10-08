package com.customer.management.exception.contract;

public class InvalidContractType extends RuntimeException{

    public InvalidContractType(String errorMessage) {
        super(errorMessage);
    }
}
