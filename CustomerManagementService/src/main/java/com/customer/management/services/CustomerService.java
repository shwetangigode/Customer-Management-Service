package com.customer.management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.customer.management.entity.Customer;
import com.customer.management.exception.customer.CustomerNotFoundException;
import com.customer.management.model.CustomerModel;
import com.customer.management.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<CustomerModel> createCustomer(CustomerModel customerModel) {
        CustomerModel customerModelObj = new CustomerModel();

        customerModelObj.setAccountType(customerModel.getAccountType());
        customerModelObj.setBusinessRequirements(customerModel.getBusinessRequirements());
        customerModelObj.setContractType(customerModel.getContractType());
        customerModelObj.setDob(customerModel.getDob());
        customerModelObj.setName(customerModel.getName());
        customerModelObj.setNativeLocation(customerModel.getNativeLocation());
        customerModelObj.setSex(customerModel.getSex());

        Customer customerObjectToBeSaved = Customer.from(customerModel);
        Customer savedCustomer =  customerRepository.save(customerObjectToBeSaved);

        return ResponseEntity.ok(Customer.to(savedCustomer));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public ResponseEntity<CustomerModel> updateCustomer(Long customerId, CustomerModel updatedCustomerModel) {
        // Check if the customer with the given ID exists
        Optional<Customer> existingCustomer = customerRepository.findById(customerId);

        if (existingCustomer.isPresent()) {
            Customer customerModelToUpdate = getCustomerModel(updatedCustomerModel, existingCustomer.get());
            Customer savedCustomer = customerRepository.save(customerModelToUpdate);
            CustomerModel customerModel = Customer.to(savedCustomer);
            return ResponseEntity.ok(customerModel);
        } else {
            throw new CustomerNotFoundException("The Given Customer ID not found");
        }
    }

    private static Customer getCustomerModel(CustomerModel updatedCustomerModel, Customer customerToUpdate) {
        customerToUpdate.setName(updatedCustomerModel.getName());
        customerToUpdate.setSex(updatedCustomerModel.getSex());
        customerToUpdate.setDob(updatedCustomerModel.getDob());
        customerToUpdate.setNativeLocation(updatedCustomerModel.getNativeLocation());
        customerToUpdate.setAccountType(updatedCustomerModel.getAccountType());
        customerToUpdate.setBusinessRequirements(updatedCustomerModel.getBusinessRequirements());
        customerToUpdate.setContractType(updatedCustomerModel.getContractType());
        return customerToUpdate;
    }

    public boolean deleteCustomer(Long customerId) {
        // Check if the customer with the given ID exists before deleting
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true; // Deletion was successful
        } else {
            return false; // Customer with the specified ID was not found
        }
    }
}
