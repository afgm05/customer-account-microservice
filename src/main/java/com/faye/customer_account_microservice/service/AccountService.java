package com.faye.customer_account_microservice.service;

import com.faye.customer_account_microservice.enums.AccountType;
import com.faye.customer_account_microservice.model.AccountEntity;
import com.faye.customer_account_microservice.model.CustomerEntity;

public interface AccountService {
    AccountEntity createAccount(AccountType accountType, String accountNumber, Double initialBalance, Long CustomerNumber);
}
