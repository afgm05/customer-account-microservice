package com.faye.customer_account_microservice.service.impl;

import com.faye.customer_account_microservice.enums.AccountType;
import com.faye.customer_account_microservice.model.AccountEntity;
import com.faye.customer_account_microservice.model.CustomerEntity;
import com.faye.customer_account_microservice.repository.AccountRepository;
import com.faye.customer_account_microservice.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountEntity createAccount(AccountType accountType, String accountNumber, Double initialBalance, Long customerNumber) {

        AccountEntity account = new AccountEntity();
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountType);
        account.setAvailableBalance(initialBalance);
        account.setCustomerNumber(customerNumber);

        return accountRepository.save(account);
    }
}
