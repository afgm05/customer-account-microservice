package com.faye.customer_account_microservice.mapper.impl;

import com.faye.customer_account_microservice.dto.AccountResponseDto;
import com.faye.customer_account_microservice.dto.CustomerInfoResponseDto;
import com.faye.customer_account_microservice.mapper.Mapper;
import com.faye.customer_account_microservice.model.AccountEntity;
import com.faye.customer_account_microservice.model.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerInfoResponseMapper implements Mapper<CustomerEntity, CustomerInfoResponseDto> {

    private final ModelMapper modelMapper;

    public CustomerInfoResponseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerInfoResponseDto mapTo(CustomerEntity customerEntity) {
        CustomerInfoResponseDto responseDto = modelMapper.map(customerEntity, CustomerInfoResponseDto.class);

        List<AccountResponseDto> savingsAccounts = customerEntity.getAccounts().stream()
                .filter(account -> account.getAccountType().name().equalsIgnoreCase("SAVINGS"))
                .map(this::mapToAccountResponseDto)
                .collect(Collectors.toList());

        List<AccountResponseDto> checkingAccounts = customerEntity.getAccounts().stream()
                .filter(account -> account.getAccountType().name().equalsIgnoreCase("CHECKING"))
                .map(this::mapToAccountResponseDto)
                .collect(Collectors.toList());

        responseDto.setSavingsAccounts(savingsAccounts);
        responseDto.setCheckingAccounts(checkingAccounts);

        return responseDto;
    }

    @Override
    public CustomerEntity mapFrom(CustomerInfoResponseDto customerInfoResponseDto) {
        return modelMapper.map(customerInfoResponseDto, CustomerEntity.class);
    }

    private AccountResponseDto mapToAccountResponseDto(AccountEntity account) {
        return new AccountResponseDto(
                account.getAccountNumber(),
                account.getAccountType(),
                account.getAvailableBalance()
        );
    }

}
