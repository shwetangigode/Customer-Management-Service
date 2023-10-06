package com.customer.management.entity;

import java.sql.Timestamp;
import java.util.List;

import com.customer.management.validators.interfaces.DateOfBirthConstraint;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is required")
	private String name;

	@Pattern(regexp = "M|F", message = "Sex should be 'M' or 'F'")
	private String sex;

	@DateOfBirthConstraint
	private String dob;
	private String nativeLocation;
	private String accountType;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private List<BusinessRequirement> businessRequirements;

	@ManyToOne
	@JoinColumn(name = "contract_type_id")
	private ContractType contractType;

	private Timestamp created_on;
    private String created_by;
    private Timestamp modified_on;
    private String modified_by;
}
