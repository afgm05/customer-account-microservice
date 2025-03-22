package com.faye.customer_account_microservice.service;

import com.faye.customer_account_microservice.dto.CustomerDto;
import com.faye.customer_account_microservice.model.CustomerEntity;

import java.util.Optional;

public interface CustomerAccountService {
    CustomerEntity createCustomerAccount(CustomerDto customerDto);
}
