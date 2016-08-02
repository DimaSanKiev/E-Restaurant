package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.Employee;

public interface EmployeeDao extends GenericDao<Employee> {

    /**
     * Returns the employee that matches the passed in email.
     *
     * @param email employee's email address
     * @return employee with the given {@code email} or {@literal null} if none found
     */
    Employee findByEmail(String email);
}
