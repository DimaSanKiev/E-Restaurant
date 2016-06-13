package com.bionic.edu.service;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.exception.CustomerBlockedException;
import com.bionic.edu.service.generic.ServiceInterface;

public interface CustomerService extends ServiceInterface<Customer> {

    Customer findByEmail(String email);

    Customer signIn(String email, String password) throws CustomerBlockedException;
}
