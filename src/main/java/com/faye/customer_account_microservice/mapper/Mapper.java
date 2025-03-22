package com.faye.customer_account_microservice.mapper;

public interface Mapper<A, B> {

    B mapTo(A a);
    A mapFrom(B b);

}
