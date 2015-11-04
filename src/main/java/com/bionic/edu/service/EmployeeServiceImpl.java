package com.bionic.edu.service;

import com.bionic.edu.dao.EmployeeDao;
import com.bionic.edu.entity.Employee;
import com.bionic.edu.util.Crypto;
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
        Employee employee = employeeDao.findById(id);
        if (employee != null)
            employee.setPassword(Crypto.encrypt(employee.getPassword()));
        return employee;
    }

    @Override
    public Employee findByEmail(String email) {
        Employee employee = employeeDao.findByEmail(email);
        employee.setPassword(Crypto.encrypt(employee.getPassword()));
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = employeeDao.findAll();
        for (Employee employee : employees) {
            employee.setPassword(Crypto.encrypt(employee.getPassword()));
        }
        return employees;
    }

    @Transactional
    @Override
    public void save(Employee employee) {
        employee.setPassword(Crypto.encrypt(employee.getPassword()));
        employeeDao.save(employee);
    }

    @Transactional
    @Override
    public void delete(int id) {
        employeeDao.delete(id);
    }


    @Override
    public Employee login(String email, String password) {
        String decryptPass = Crypto.encrypt(password);
        Employee employee = employeeDao.findByEmail(email);
        if (employee.getPassword().equals(decryptPass)) {
            return employee;
        } else
            return null;
    }

    @Override
    public void setReadiness(Employee employee, boolean isReady) {
        employeeDao.setReadiness(employee, isReady);
    }

}
