package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.Employee;

public interface EmployeeDao extends GenericDao<Employee> {

    Employee findByEmail(String email);
}
