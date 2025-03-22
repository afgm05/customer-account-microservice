package com.faye.customer_account_microservice.model;

import com.faye.customer_account_microservice.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @Column(name = "account_number", length = 15, nullable = false, unique = true)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Account type is required")
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Column(name = "available_balance", nullable = true)
    private Double availableBalance;

    @Column(name = "customer_number", nullable = false)
    private Long customerNumber;
}
