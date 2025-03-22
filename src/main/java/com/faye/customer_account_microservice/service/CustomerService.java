package com.faye.customer_account_microservice.service;

import com.faye.customer_account_microservice.model.CustomerEntity;

import java.util.Optional;

public interface CustomerService {

    CustomerEntity save(CustomerEntity customerEntity);

    Optional<CustomerEntity> getByCustomerNumber(Long customerNumber);
}
