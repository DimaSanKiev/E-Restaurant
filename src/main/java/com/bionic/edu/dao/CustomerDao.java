package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.Customer;

public interface CustomerDao extends GenericDao<Customer> {

    Customer findByEmail(String email);

}
