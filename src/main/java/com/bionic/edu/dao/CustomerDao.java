package com.bionic.edu.dao;

import com.bionic.edu.entity.Customer;

import java.util.List;

public interface CustomerDao {

    Customer findById(int id);

    Customer findByEmail(String email);

    List<Customer> findAll();

    void save(Customer customer);

    void delete(int id);


    Customer login(String email, String password);
}
