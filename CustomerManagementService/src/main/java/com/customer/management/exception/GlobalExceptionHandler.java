package com.customer.management.exception;

import com.customer.management.exception.contract.InvalidContractID;
import com.customer.management.exception.contract.InvalidContractType;
import com.customer.management.exception.customer.CustomerNotFoundException;
import com.customer.management.exception.customer.InvalidDateOfBirthException;
import com.customer.management.exception.customer.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCustomerNotFoundException(CustomerNotFoundException ex) {

        Map<String, String> map = new HashMap<>();
        map.put("exception", "Input data is invalid");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Map<String, String>> invalidData(InvalidInputException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("exception", "Input data is invalid");
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidContractType.class)
    public ResponseEntity<Map<String, String>> invalidContract(InvalidContractType ex) {
        Map<String, String> map = new HashMap<>();
        map.put("exception", "Invalid contract exception");
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidContractID.class)
    public ResponseEntity<Map<String, String>> invalidContractID(InvalidContractID ex) {
        Map<String, String> map = new HashMap<>();
        map.put("exception", "Invalid contract ID");
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDateOfBirthException.class)
    public ResponseEntity<Map<String, String>> invalidDateOfBirth(InvalidDateOfBirthException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("exception", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("exception", Objects.requireNonNull(ex.getDetailMessageArguments())[1].toString());
        return new ResponseEntity<>(map, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Map<String, String>> methodNotValidException(SQLException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("exception", Objects.requireNonNull(ex.getMessage()));
        return new ResponseEntity<>(map, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
