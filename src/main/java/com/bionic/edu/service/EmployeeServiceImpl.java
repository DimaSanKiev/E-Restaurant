package com.bionic.edu.service;

import com.bionic.edu.dao.EmployeeDao;
import com.bionic.edu.entity.Employee;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeDao employeeDao;

    @Override
    public Employee findById(int id) {
        return employeeDao.findById(id);
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeDao.findByEmail(email);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Transactional
    @Override
    public void add(Employee employee) {
        employeeDao.add(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    @Transactional
    @Override
    public void delete(int id) {
        employeeDao.delete(id);
    }
}
