package com.faye.customer_account_microservice.dto;

import com.faye.customer_account_microservice.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {
    private String accountNumber;
    private AccountType accountType;
    private Double availableBalance;
}
