package com.bionic.edu.service;

import com.bionic.edu.entity.Employee;
import com.bionic.edu.exception.EmployeeUnavailableException;

import java.util.List;

public interface EmployeeService {

    Employee findById(int id);

    Employee findByEmail(String email);

    List<Employee> findAll();

    void save(Employee employee);

    void delete(int id);


    Employee signIn(String email, String password) throws EmployeeUnavailableException;

    void setReadiness(Employee employee, boolean isReady);
}
