package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.DaoInterface;
import com.bionic.edu.entity.Employee;

public interface EmployeeDao extends DaoInterface<Employee> {

    Employee findByEmail(String email);

    void setReadiness(Employee employee, boolean isReady);
}
