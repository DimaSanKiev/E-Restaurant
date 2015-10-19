package com.bionic.edu.service;

import com.bionic.edu.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findById(int id);

    Employee findByEmail(String email);

    List<Employee> findAll();

    void add(Employee employee);

    void update(Employee employee);

    void delete(int id);


    void register(Employee employee);

    Employee login(String email, String password);

    void setReadiness(Employee employee, boolean isReady);
}
