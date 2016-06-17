package com.bionic.edu.service;

import com.bionic.edu.entity.Employee;
import com.bionic.edu.exception.BadCredentialsException;
import com.bionic.edu.exception.EmployeeNotReadyException;
import com.bionic.edu.service.generic.GenericService;

public interface EmployeeService extends GenericService<Employee> {

    Employee findByEmail(String email);

    Employee signIn(String email, String password) throws BadCredentialsException, EmployeeNotReadyException;

    void setReadiness(Employee employee, boolean isReady);
}
