package com.customer.management.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.customer.management.entity.ContractType;
import com.customer.management.entity.Customer;
import com.customer.management.repository.ContractTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContractTypeService {

    private final ContractTypeRepository contractTypeRepository;

    @Autowired
    public ContractTypeService(ContractTypeRepository contractTypeRepository) {
        this.contractTypeRepository = contractTypeRepository;
    }

    public ResponseEntity<ContractType> createContractType(ContractType contractType) {
    	return ResponseEntity.ok(contractTypeRepository.save(contractType));
    }

    public List<ContractType> getAllContractTypes() {
        return contractTypeRepository.findAll();
    }

    public Optional<ContractType> getContractTypeById(UUID contractTypeId) {
        return contractTypeRepository.findById(contractTypeId);
    }

    public ContractType updateContractType(UUID contractTypeId, ContractType updatedContractType) {
        // Check if the contract type with the given ID exists
        Optional<ContractType> existingContractType = contractTypeRepository.findById(contractTypeId);

        if (existingContractType.isPresent()) {
            ContractType contractTypeToUpdate = existingContractType.get();

            // Update fields as needed
            contractTypeToUpdate.setName(updatedContractType.getName());

            // Save the updated contract type
            return contractTypeRepository.save(contractTypeToUpdate);
        } else {
            // Contract type not found
            return null;
        }
    }

    public boolean deleteContractType(UUID contractTypeId) {
        // Check if the contract type with the given ID exists before deleting
        if (contractTypeRepository.existsById(contractTypeId)) {
            contractTypeRepository.deleteById(contractTypeId);
            return true; // Deletion was successful
        }else {
        	return false; // Contract type with the specified ID was not found
        }
    }
}
