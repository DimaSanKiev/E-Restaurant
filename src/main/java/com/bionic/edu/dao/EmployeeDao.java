package com.bionic.edu.dao;

import com.bionic.edu.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee findById(int id);

    Employee findByEmail(String email);

    List<Employee> findAll();

    void add(Employee customer);

    void update(Employee customer);
}
