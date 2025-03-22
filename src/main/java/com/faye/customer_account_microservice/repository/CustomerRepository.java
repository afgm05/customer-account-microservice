package com.faye.customer_account_microservice.repository;

import com.faye.customer_account_microservice.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByCustomerNumber(Long customerNumber);
}
