package com.faye.customer_account_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountCreationValidationErrorDto {
    private int transactionStatusCode;
    private String transactionStatusDescription;
}
