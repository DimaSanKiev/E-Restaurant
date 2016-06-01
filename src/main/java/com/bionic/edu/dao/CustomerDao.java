package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.DaoInterface;
import com.bionic.edu.entity.Customer;

public interface CustomerDao extends DaoInterface<Customer> {

    Customer findByEmail(String email);

}
