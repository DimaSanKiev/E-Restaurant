package com.bionic.edu.service;

import com.bionic.edu.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findById(int id);

    Employee findByEmail(String email);

    List<Employee> findAll();

    void save(Employee employee);

    void delete(int id);


    Employee login(String email, String password);

    void setReadiness(Employee employee, boolean isReady);
}
