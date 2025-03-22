package com.faye.customer_account_microservice.enums;

import lombok.Getter;

@Getter
public enum AccountType {
    SAVINGS("S"),
    CHECKING("C");

    private final String code;

    AccountType(String code) {
        this.code = code;
    }

    public static AccountType fromCode(String code) {
        for (AccountType accountType : AccountType.values()) {
            if (accountType.getCode().equals(code)) {
                return accountType;
            }
        }
        throw new IllegalArgumentException("Invalid AccountType code: " + code);
    }
}
