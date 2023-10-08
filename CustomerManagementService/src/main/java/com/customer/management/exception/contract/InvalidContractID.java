package com.customer.management.exception.contract;

public class InvalidContractID extends RuntimeException {

	public InvalidContractID(String errorMessage) {
		super(errorMessage);
	}
}
