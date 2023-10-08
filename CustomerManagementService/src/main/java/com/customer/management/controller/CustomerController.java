package com.customer.management.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.management.entity.Customer;
import com.customer.management.exception.customer.CustomerNotFoundException;
import com.customer.management.exception.customer.InvalidInputException;
import com.customer.management.model.CustomerModel;
import com.customer.management.services.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Transactional
	@PostMapping
	public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerModel customerModel) {
		if (customerModel == null) {
			throw new InvalidInputException("Given data is invalid please pass it right information");
		}
		ResponseEntity<CustomerModel> createdCustomer = customerService.createCustomer(customerModel);
		return ResponseEntity.ok(createdCustomer.getBody());
	}

	@GetMapping
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Object> getCustomerById(@PathVariable Long customerId) {
		Optional<Customer> customerOptional = customerService.getCustomerById(customerId);

		return customerOptional
				.<ResponseEntity<Object>>map(customerModel -> ResponseEntity.status(HttpStatus.OK).body(customerModel))
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Customer not found with ID: " + customerId));
	}

	@PutMapping
	public ResponseEntity<CustomerModel> updateCustomer(@RequestBody CustomerModel updatedCustomerModel) {
		ResponseEntity<CustomerModel> customer = customerService.updateCustomer(updatedCustomerModel.getId(),
				updatedCustomerModel);
		if (customer == null) {
			throw new CustomerNotFoundException("The given Customer data could not be found in database");
		}
		return ResponseEntity.ok(customer.getBody());
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
		if (customerService.deleteCustomer(customerId)) {
			return ResponseEntity.ok("Customer Deleted Successfully with the ID ->" + customerId);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Not Found");
		}
	}
}// class
