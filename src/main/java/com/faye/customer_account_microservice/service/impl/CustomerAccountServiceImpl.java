package com.faye.customer_account_microservice.service.impl;

import com.faye.customer_account_microservice.dto.CustomerDto;
import com.faye.customer_account_microservice.enums.AccountType;
import com.faye.customer_account_microservice.mapper.Mapper;
import com.faye.customer_account_microservice.model.CustomerEntity;
import com.faye.customer_account_microservice.service.AccountService;
import com.faye.customer_account_microservice.service.CustomerAccountService;
import com.faye.customer_account_microservice.service.CustomerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

    private final CustomerService customerService;
    private final AccountService accountService;
    private final Mapper<CustomerEntity, CustomerDto> customerMapper;
    // Thread-safe counters
    private final AtomicLong customerCounter = new AtomicLong(1);
    private final AtomicLong accountCounter = new AtomicLong(1);

    public CustomerAccountServiceImpl(CustomerService customerService, AccountService accountService, Mapper<CustomerEntity, CustomerDto> customerMapper) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerEntity createCustomerAccount(CustomerDto customerDto) {

        CustomerEntity customerEntity = customerMapper.mapFrom(customerDto);
        customerEntity.setCustomerNumber(generateCustomerNumber());

        CustomerEntity savedCustomer = customerService.save(customerEntity);

        AccountType accountType;
        try {
            accountType = AccountType.fromCode(customerDto.getAccountType());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid account type: " + customerDto.getAccountType(), e);
        }

        accountService.createAccount(
                accountType,
                generateAccountNumber(),
                0.0, // Default initial balance
                savedCustomer.getCustomerNumber()
        );

        return savedCustomer;
    }

    private Long generateCustomerNumber() {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
        long uniqueId = customerCounter.getAndIncrement() % 1000;
        return Long.parseLong(dateTime + uniqueId);
    }

    private synchronized String generateAccountNumber() {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm"));
        long uniqueId = accountCounter.getAndIncrement() % 1000;
        return "ACC" + dateTime + uniqueId;
    }

}
