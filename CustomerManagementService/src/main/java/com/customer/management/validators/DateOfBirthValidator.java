package com.customer.management.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.customer.management.exception.customer.InvalidDateOfBirthException;
import com.customer.management.validators.interfaces.DateOfBirthConstraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateOfBirthValidator implements ConstraintValidator<DateOfBirthConstraint, String> {

	private final String DATE_FORMAT = "dd-MM-yyyy";
	private final int MAX_YEAR = 2002;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		dateFormat.setLenient(false);

		try {
			Date dateOfBirth = dateFormat.parse(value);
			int year = dateOfBirth.getYear() + 1900;

			// Check if the year is less than 2002
			if (year < MAX_YEAR) {
				return true;
			}
			throw new InvalidDateOfBirthException("Invalid date of birth format or must be less than 2002");
		} catch (ParseException e) {
			return false; // Date format is not valid
		}
	}
}
