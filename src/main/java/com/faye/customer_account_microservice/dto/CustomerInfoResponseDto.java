package com.faye.customer_account_microservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.faye.customer_account_microservice.model.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfoResponseDto {

    @JsonProperty("customerNumber")
    private String customerNumber;

    @JsonProperty("customerName")
    private String name;

    @JsonProperty("customerMobile")
    private String mobile;

    @JsonProperty("customerEmail")
    private String email;

    @JsonProperty("address1")
    private String address1;

    @JsonProperty("address2")
    private String address2;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("savings")
    private List<AccountResponseDto> savingsAccounts;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("checking")
    private List<AccountResponseDto> checkingAccounts;

    @JsonProperty("transactionStatusCode")
    private int transactionStatusCode;

    @JsonProperty("transactionStatusDescription")
    private String transactionStatusDescription;
}
