package com.bionic.edu.service;

import com.bionic.edu.dao.EmployeeDao;
import com.bionic.edu.entity.Employee;
import com.bionic.edu.exception.EmployeeUnavailableException;
import com.bionic.edu.util.WeakCrypto;
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
        Employee employee = employeeDao.findById(id);
        if (employee != null)
            employee.setPassword(WeakCrypto.encrypt(employee.getPassword()));
        return employee;
    }

    @Override
    public Employee findByEmail(String email) {
        Employee employee = employeeDao.findByEmail(email);
        employee.setPassword(WeakCrypto.encrypt(employee.getPassword()));
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = employeeDao.findAll();
        for (Employee employee : employees) {
            employee.setPassword(WeakCrypto.encrypt(employee.getPassword()));
        }
        return employees;
    }

    @Transactional
    @Override
    public void save(Employee employee) {
        employee.setPassword(WeakCrypto.encrypt(employee.getPassword()));
        employeeDao.save(employee);
    }

    @Transactional
    @Override
    public void delete(int id) {
        employeeDao.delete(id);
    }


    @Override
    public Employee signIn(String email, String password) throws EmployeeUnavailableException {
        Employee employee = employeeDao.findByEmail(email);
        if (employee != null) {
            if (!employee.isReady()) throw new EmployeeUnavailableException("Employee is not ready.");
            return employee;
        }
        return null;
    }

    @Override
    public void setReadiness(Employee employee, boolean isReady) {
        employeeDao.setReadiness(employee, isReady);
    }

}
