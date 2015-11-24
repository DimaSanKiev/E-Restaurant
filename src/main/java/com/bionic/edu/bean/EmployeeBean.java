package com.bionic.edu.bean;

import com.bionic.edu.entity.Employee;
import com.bionic.edu.service.EmployeeService;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Scope("session")
public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = -6003880259950580877L;

    private List<Employee> employees = null;
    private Employee employee = null;
    @Inject
    private EmployeeService employeeService;

    public EmployeeBean() {
        employee = new Employee();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public void refreshList() {
        employees = employeeService.findAll();
    }

    public String saveEmployee() {
        employeeService.save(employee);
        return "EmployeeList";
    }

    public String addEmployee() {
        employee = new Employee();
        return "NewEmployee";
    }

    public String updateEmployee(String id) {
        int n = Integer.valueOf(id);
        employee = employeeService.findById(n);
        return "NewEmployee";
    }

}
