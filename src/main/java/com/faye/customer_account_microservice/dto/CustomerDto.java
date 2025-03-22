package com.faye.customer_account_microservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    @JsonProperty("customerName")
    @NotBlank(message = "Customer name is required")
    private String name;

    @JsonProperty("customerMobile")
    @NotBlank(message = "Customer mobile is required")
    private String mobile;

    @JsonProperty("customerEmail")
    @Email(message = "Customer email should be valid")
    @NotBlank(message = "Customer email is required")
    private String email;

    @JsonProperty("address1")
    @NotBlank(message = "Address1 is required")
    private String address1;

    @JsonProperty("address2")
    private String address2;

    @JsonProperty("accountType")
    @NotBlank(message = "Account type is required")
    @Pattern(regexp = "S|C", message = "Account type must be 'S' (Savings) or 'C' (Checking)")
    private String accountType;
}
