package com.customer.management.customercontroller.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.customer.management.controller.CustomerController;
import com.customer.management.entity.Customer;
import com.customer.management.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;  // If ObjectMapper is used

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

//    @Test
	/*
	 * public void testCreateCustomer() throws Exception { Customer customer = new
	 * Customer(); // Create a sample customer object
	 * when(customerService.createCustomer(any(Customer.class))).thenReturn(
	 * ResponseEntity.ok(customer));
	 * 
	 * mockMvc.perform(post("/customers") .contentType(MediaType.APPLICATION_JSON)
	 * .content(new ObjectMapper().writeValueAsString(customer)))
	 * .andExpect(status().isOk()); }
	 */

    @Test
    public void testGetAllCustomers() throws Exception {
        List<Customer> customerList = new ArrayList<>(); // Create a sample list of customers
        when(customerService.getAllCustomers()).thenReturn(customerList);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk());
    }

//    @Test
    public void testGetCustomerById() throws Exception {
        Long customerId = 1L;
        Customer customer = new Customer(); // Create a sample customer object
        when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/customers/{customerId}", customerId))
                .andExpect(status().isOk());
    }

//    @Test
    public void testUpdateCustomer() throws Exception {
        Long customerId = 1L;
        Customer updatedCustomer = new Customer(); // Create a sample updated customer object
        when(customerService.updateCustomer(eq(customerId), any(Customer.class)))
            .thenReturn(ResponseEntity.ok(updatedCustomer));

        mockMvc.perform(put("/customers/{customerId}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedCustomer)))
                .andExpect(status().isOk());
    }

//    @Test
    public void testDeleteCustomer() throws Exception {
        Long customerId = 1L;
        doNothing().when(customerService).deleteCustomer(customerId);

        mockMvc.perform(delete("/customers/{customerId}", customerId))
                .andExpect(status().isOk());
    }
}
