package com.customer.management.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.customer.management.entity.Customer;
import com.customer.management.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public ResponseEntity<Customer> createCustomer(Customer customer) {
		Customer customerObj = new Customer();

		customerObj.setAccountType(customer.getAccountType());
		customerObj.setBusinessRequirements(customer.getBusinessRequirements());
		customerObj.setContractType(customer.getContractType());
		customerObj.setDob(customer.getDob());
		customerObj.setName(customer.getName());
		customerObj.setNativeLocation(customer.getNativeLocation());
		customerObj.setSex(customer.getSex());
		customerObj.setCreated_by("Admin");
		customerObj.setModified_by("Admin");
		customerObj.setModified_on(timestamp);
		customerObj.setCreated_on(timestamp);
		return ResponseEntity.ok(customerRepository.save(customerObj));
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Optional<Customer> getCustomerById(Long customerId) {
		return customerRepository.findById(customerId);
	}

	public ResponseEntity<Customer> updateCustomer(Long customerId, Customer updatedCustomer) {
		// Check if the customer with the given ID exists
		Optional<Customer> existingCustomer = customerRepository.findById(customerId);

		if (existingCustomer.isPresent()) {
			Customer customerToUpdate = existingCustomer.get();

			// Update fields as needed
			customerToUpdate.setName(updatedCustomer.getName());
			customerToUpdate.setSex(updatedCustomer.getSex());
			customerToUpdate.setDob(updatedCustomer.getDob());
			customerToUpdate.setNativeLocation(updatedCustomer.getNativeLocation());
			customerToUpdate.setAccountType(updatedCustomer.getAccountType());
			customerToUpdate.setBusinessRequirements(updatedCustomer.getBusinessRequirements());
			customerToUpdate.setContractType(updatedCustomer.getContractType());
			customerToUpdate.setModified_by("admin");
			customerToUpdate.setModified_on(timestamp);
			// Save the updated customer
			Customer savedCustomer = customerRepository.save(customerToUpdate);

			return ResponseEntity.ok(savedCustomer); // Return updated customer
		} else {
			Customer notFoundCustomer = new Customer();
			notFoundCustomer.setName("Customer not found with ID: " + customerId);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundCustomer);
		}
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
