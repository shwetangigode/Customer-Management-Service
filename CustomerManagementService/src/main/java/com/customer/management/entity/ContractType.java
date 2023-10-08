package com.customer.management.entity;

import java.util.UUID;

import com.customer.management.model.ContractTypeModel;
import com.fasterxml.jackson.databind.ObjectMapper;

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


    public static ContractType from(ContractTypeModel contractTypeModel) {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.convertValue(
                contractTypeModel, ContractType.class);

    }

    public static ContractTypeModel to(ContractType contractType) {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.convertValue(
                contractType, ContractTypeModel.class);

    }
}