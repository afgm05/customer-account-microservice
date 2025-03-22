package com.faye.customer_account_microservice.mapper.impl;

import com.faye.customer_account_microservice.dto.CustomerDto;
import com.faye.customer_account_microservice.mapper.Mapper;
import com.faye.customer_account_microservice.model.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements Mapper<CustomerEntity, CustomerDto> {

    private final ModelMapper modelMapper;

    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        // Ignore mapping for 'customerNumber'
        this.modelMapper.typeMap(CustomerDto.class, CustomerEntity.class)
                .addMappings(mapper -> mapper.skip(CustomerEntity::setCustomerNumber));
    }

    @Override
    public CustomerDto mapTo(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerDto.class);
    }

    @Override
    public CustomerEntity mapFrom(CustomerDto customerDto) {
        return modelMapper.map(customerDto, CustomerEntity.class);
    }
}

