package com.customer.management.model;

import java.util.List;

import com.customer.management.entity.Auditable;
import com.customer.management.entity.BusinessRequirement;
import com.customer.management.entity.ContractType;
import com.customer.management.validators.interfaces.DateOfBirthConstraint;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerModel extends Auditable {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Pattern(regexp = "M|F", message = "Sex should be 'M' or 'F'")
    private String sex;

    @DateOfBirthConstraint
    private String dob;
    private String nativeLocation;
    private String accountType;

    private List<BusinessRequirement> businessRequirements;

    private ContractType contractType;


}
