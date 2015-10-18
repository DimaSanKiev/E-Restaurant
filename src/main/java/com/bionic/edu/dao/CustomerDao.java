package com.bionic.edu.dao;

import com.bionic.edu.entity.Customer;

import java.util.List;

public interface CustomerDao {

    Customer findById(int id);

    Customer findByEmail(String email);

    List<Customer> findAll();

    void add(Customer customer);

    void update(Customer customer);

    void delete(int id);


    void login(String email, String password);
}
