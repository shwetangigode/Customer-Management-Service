package com.customer.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.management.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
