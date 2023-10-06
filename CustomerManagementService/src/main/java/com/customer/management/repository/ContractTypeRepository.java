package com.customer.management.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.management.entity.ContractType;

public interface ContractTypeRepository extends JpaRepository<ContractType, UUID>{

}
