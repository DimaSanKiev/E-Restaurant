package com.bionic.edu.service;

import com.bionic.edu.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer findById(int id);

    Customer findByEmail(String email);

    List<Customer> findAll();

    void update(Customer customer);

    void delete(int id);


    void register(Customer customer);

    Customer login(String login, String password);
}
