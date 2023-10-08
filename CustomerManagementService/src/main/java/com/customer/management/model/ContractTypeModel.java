package com.customer.management.model;

import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ContractTypeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private UUID id;
	private String name;
}