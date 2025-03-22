package com.faye.customer_account_microservice.util;

import com.faye.customer_account_microservice.dto.CustomerAccountCreationValidationErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

public class ValidationErrorUtil {

    public ResponseEntity<CustomerAccountCreationValidationErrorDto> buildCustomerCreationErrorResponse(BindingResult bindingResult) {

        String errorMessages = bindingResult.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .reduce((message1, message2) -> message1 + "; " + message2)
                .orElse("");

        CustomerAccountCreationValidationErrorDto responseDTO = new CustomerAccountCreationValidationErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                errorMessages.trim()
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
