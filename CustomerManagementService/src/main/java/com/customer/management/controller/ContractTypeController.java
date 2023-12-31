package com.customer.management.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.management.entity.ContractType;
import com.customer.management.exception.contract.InvalidContractID;
import com.customer.management.exception.contract.InvalidContractType;
import com.customer.management.model.ContractTypeModel;
import com.customer.management.services.ContractTypeService;

@RestController
@RequestMapping("/contract_types")
public class ContractTypeController {

    private final ContractTypeService contractTypeService;

    @Autowired
    public ContractTypeController(ContractTypeService contractTypeService) {
        this.contractTypeService = contractTypeService;
    }

    @PostMapping
    public ResponseEntity<Object> createContractType(@RequestBody ContractTypeModel contractTypeModel) {
        if (contractTypeModel == null) {
            throw new InvalidContractType("Given contract type is invalid");
        }
        ResponseEntity<ContractType> createdContractType = contractTypeService.createContractType(contractTypeModel);
        return ResponseEntity.ok(createdContractType.getBody());
    }

    @GetMapping
    public List<ContractType> getAllContractTypes() {
        return contractTypeService.getAllContractTypes();
    }

    @GetMapping("/{contractTypeId}")
    public ResponseEntity<Object> getContractTypeById(@PathVariable UUID contractTypeId) {
        Optional<ContractType> contractTypeOptional = contractTypeService.getContractTypeById(contractTypeId);
        return contractTypeOptional.<ResponseEntity<Object>>map(contractTypeModel ->
                ResponseEntity.status(HttpStatus.OK).body(contractTypeModel)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("ContractType not found with ID: " + contractTypeId));
    }

    @PutMapping("/{contractTypeId}")
    public ResponseEntity<Object> updateContractType(@PathVariable UUID contractTypeId,
                                                     @RequestBody ContractTypeModel updatedContractTypeModel) {
        ContractType contractTypeModel = contractTypeService
                .updateContractType(contractTypeId, updatedContractTypeModel);
        if (contractTypeModel == null) {
        	throw new InvalidContractID("Given contract ID is invalid");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contractTypeModel);
    }

    @DeleteMapping("/{contractTypeId}")
    public ResponseEntity<String> deleteContractType(@PathVariable UUID contractTypeId) {
        if (contractTypeService.deleteContractType(contractTypeId)) {
            return ResponseEntity.ok("Contract Type Deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Contract Type ID " + contractTypeId + "not Found.");
        }
    }
}
