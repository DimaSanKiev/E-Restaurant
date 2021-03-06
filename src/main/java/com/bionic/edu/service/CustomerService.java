package com.bionic.edu.service;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.exception.BadCredentialsException;
import com.bionic.edu.exception.CustomerBlockedException;
import com.bionic.edu.service.generic.GenericService;

public interface CustomerService extends GenericService<Customer> {

    void update(Customer customer);

    void blockUnblockCustomer(int customerId);

    Customer findByEmail(String email);

    Customer signIn(String email, String password) throws BadCredentialsException, CustomerBlockedException;
}
