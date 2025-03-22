package com.faye.customer_account_microservice.controller;

import com.faye.customer_account_microservice.dto.TransactionStatusDto;
import com.faye.customer_account_microservice.dto.CustomerDto;
import com.faye.customer_account_microservice.dto.CustomerInfoResponseDto;
import com.faye.customer_account_microservice.mapper.impl.CustomerInfoResponseMapper;
import com.faye.customer_account_microservice.model.CustomerEntity;
import com.faye.customer_account_microservice.service.CustomerAccountService;
import com.faye.customer_account_microservice.service.CustomerService;
import com.faye.customer_account_microservice.util.ValidationErrorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class CustomerAccountController {

    private final CustomerAccountService customerAccountService;
    private final CustomerService customerService;
    private final CustomerInfoResponseMapper customerInfoResponseMapper;

    public CustomerAccountController(CustomerAccountService customerAccountService, CustomerService customerService, CustomerInfoResponseMapper customerInfoResponseMapper) {
        this.customerAccountService = customerAccountService;
        this.customerService = customerService;
        this.customerInfoResponseMapper = customerInfoResponseMapper;
    }

    @PostMapping(path = "/api/v1/account")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ValidationErrorUtil validationErrorUtil = new ValidationErrorUtil();
            return validationErrorUtil.buildCustomerCreationErrorResponse(bindingResult);
        }

        CustomerEntity savedCustomerEntity = customerAccountService.createCustomerAccount(customerDto);

        TransactionStatusDto response = new TransactionStatusDto(
                savedCustomerEntity.getCustomerNumber(),
                HttpStatus.CREATED.value(),
                "Customer account created"
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/api/v1/account/{customerNumber}")
    public ResponseEntity<?> getCustomerInfo(@PathVariable("customerNumber") Long customerNumber) {

        Optional<CustomerEntity> customerEntityOptional = customerService.getByCustomerNumber(customerNumber);

        if (customerEntityOptional.isPresent()) {
            CustomerEntity customerEntity = customerEntityOptional.get();
            CustomerInfoResponseDto responseDto = customerInfoResponseMapper.mapTo(customerEntity);

            responseDto.setTransactionStatusCode(302);
            responseDto.setTransactionStatusDescription("Customer Account found");

            return new ResponseEntity<>(responseDto, HttpStatus.FOUND);
        } else {
            TransactionStatusDto notFoundResponse = new TransactionStatusDto(
                    null,
                    401,
                    "Customer not found"
            );

            return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
        }

    }

}
