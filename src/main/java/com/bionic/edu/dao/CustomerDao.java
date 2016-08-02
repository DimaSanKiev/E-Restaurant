package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.Customer;

public interface CustomerDao extends GenericDao<Customer> {

    /**
     * Returns the customer that matches the passed in email.
     *
     * @param email customer's email address
     * @return customer with the given {@code email} or {@literal null} if none found
     */
    Customer findByEmail(String email);

}
