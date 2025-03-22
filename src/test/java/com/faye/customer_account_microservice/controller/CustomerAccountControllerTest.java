package com.faye.customer_account_microservice.controller;

import com.faye.customer_account_microservice.dto.CustomerDto;
import com.faye.customer_account_microservice.dto.CustomerInfoResponseDto;
import com.faye.customer_account_microservice.dto.TransactionStatusDto;
import com.faye.customer_account_microservice.mapper.impl.CustomerInfoResponseMapper;
import com.faye.customer_account_microservice.model.CustomerEntity;
import com.faye.customer_account_microservice.service.CustomerAccountService;
import com.faye.customer_account_microservice.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CustomerAccountControllerTest {
    @Mock
    private CustomerAccountService customerAccountService;

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerInfoResponseMapper customerInfoResponseMapper;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private CustomerAccountController customerAccountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomer() {
        CustomerDto customerDto = new CustomerDto();
        when(bindingResult.hasErrors()).thenReturn(false);

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerNumber(123L);
        when(customerAccountService.createCustomerAccount(any())).thenReturn(customerEntity);

        ResponseEntity<?> response = customerAccountController.createCustomer(customerDto, bindingResult);

        assertEquals(201, response.getStatusCodeValue());
        TransactionStatusDto body = (TransactionStatusDto) response.getBody();
        assertEquals(123L, body.getCustomerNumber());
        assertEquals("Customer account created", body.getTransactionStatusDescription());
    }

    @Test
    void testGetCustomerInfoFound() {
        Long customerNumber = 123L;
        CustomerEntity customerEntity = new CustomerEntity();
        when(customerService.getByCustomerNumber(customerNumber)).thenReturn(Optional.of(customerEntity));

        CustomerInfoResponseDto responseDto = new CustomerInfoResponseDto();
        responseDto.setTransactionStatusCode(302);
        responseDto.setTransactionStatusDescription("Customer Account found");
        when(customerInfoResponseMapper.mapTo(customerEntity)).thenReturn(responseDto);

        ResponseEntity<?> response = customerAccountController.getCustomerInfo(customerNumber);

        assertEquals(302, responseDto.getTransactionStatusCode());
        assertEquals("Customer Account found", responseDto.getTransactionStatusDescription());
    }

    @Test
    void testGetCustomerInfoNotFound() {
        Long customerNumber = 123L;
        when(customerService.getByCustomerNumber(customerNumber)).thenReturn(Optional.empty());

        ResponseEntity<?> response = customerAccountController.getCustomerInfo(customerNumber);

        assertEquals(404, response.getStatusCodeValue());
        TransactionStatusDto body = (TransactionStatusDto) response.getBody();
        assertEquals("Customer not found", body.getTransactionStatusDescription());
    }
}
