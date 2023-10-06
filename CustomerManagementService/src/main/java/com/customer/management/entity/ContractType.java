package com.customer.management.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ContractType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private UUID id;
	private String name;
}