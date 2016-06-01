package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.DaoInterface;
import com.bionic.edu.entity.Customer;

import java.util.List;

public interface CustomerDao extends DaoInterface<Customer> {

    Customer findById(int id);

    List<Customer> findAll();

    void save(Customer customer);

    void delete(int id);

    
    Customer findByEmail(String email);

}
