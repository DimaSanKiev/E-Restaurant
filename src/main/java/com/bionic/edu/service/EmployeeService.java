package com.bionic.edu.service;

import com.bionic.edu.entity.Employee;
import com.bionic.edu.exception.EmployeeUnavailableException;
import com.bionic.edu.service.generic.ServiceInterface;

public interface EmployeeService extends ServiceInterface<Employee> {

    Employee findByEmail(String email);

    Employee signIn(String email, String password) throws EmployeeUnavailableException;

    void setReadiness(Employee employee, boolean isReady);
}
