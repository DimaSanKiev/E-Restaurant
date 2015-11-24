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

    private String email;
    private String password;
    private boolean signedIn;
    private List<Employee> employees = null;
    private Employee employee = null;
    @Inject
    private EmployeeService employeeService;

    public EmployeeBean() {
        employee = new Employee();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
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
