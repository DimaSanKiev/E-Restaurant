package com.bionic.edu.service;

import com.bionic.edu.dao.EmployeeDao;
import com.bionic.edu.entity.Employee;
import com.bionic.edu.exception.BadCredentialsException;
import com.bionic.edu.exception.EmployeeNotReadyException;
import com.bionic.edu.util.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
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
    public Employee signIn(String email, String password) throws BadCredentialsException, EmployeeNotReadyException {
        Employee employee = employeeDao.findByEmail(email);
        if (employee == null || !employee.getPassword().equals(password)) {
            throw new BadCredentialsException("Incorrect email or password.");
        } else if (!employee.isReady()) {
            throw new EmployeeNotReadyException("Employee is not ready.");
        }
        return employee;
    }
}