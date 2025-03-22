package com.faye.customer_account_microservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatusDto {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long customerNumber;
    private int transactionStatusCode;
    private String transactionStatusDescription;
}
