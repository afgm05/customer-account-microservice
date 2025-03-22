package com.faye.customer_account_microservice.service.impl;

import com.faye.customer_account_microservice.model.CustomerEntity;
import com.faye.customer_account_microservice.repository.CustomerRepository;
import com.faye.customer_account_microservice.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerEntity save(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }

    @Override
    public Optional<CustomerEntity> getByCustomerNumber(Long customerNumber) {
        return customerRepository.findByCustomerNumber(customerNumber);
    }

}
