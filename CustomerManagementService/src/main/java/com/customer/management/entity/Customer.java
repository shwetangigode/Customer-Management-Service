package com.customer.management.entity;

import java.util.List;

import com.customer.management.model.CustomerModel;
import com.customer.management.validators.interfaces.DateOfBirthConstraint;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Customer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String sex;

    private String dob;
    private String nativeLocation;
    private String accountType;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<BusinessRequirement> businessRequirements;

    @ManyToOne
    @JoinColumn(name = "contract_type_id")
    private ContractType contractType;


    public static Customer from(CustomerModel customerModel) {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.convertValue(
                customerModel, Customer.class);

    }

    public static CustomerModel to(Customer customerModel) {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.convertValue(
                customerModel, CustomerModel.class);

    }


}
