package com.bionic.edu.service;

import com.bionic.edu.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findById(int id);

    Employee findByEmail(String email);

    List<Employee> findAll();

    void add(Employee customer);

    void update(Employee customer);
}
