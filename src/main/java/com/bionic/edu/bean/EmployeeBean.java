package com.bionic.edu.bean;

import com.bionic.edu.entity.Employee;
import com.bionic.edu.exception.EmployeeUnavailableException;
import com.bionic.edu.service.EmployeeService;
import com.bionic.edu.service.RoleService;
import com.bionic.edu.util.Crypto;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
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
    @Inject
    private RoleService roleService;

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
        employee = employeeService.findById(Integer.valueOf(id));
        return "NewEmployee";
    }

    public String signIn(String email, String password) {
        String decryptPass = Crypto.encrypt(password);
        try {
            employee = employeeService.signIn(email, decryptPass);
        } catch (NoResultException e) {
            // logger
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sign In Error", "Incorrect email or password, please try again."));
            return "employeeSignIn";
        } catch (EmployeeUnavailableException e) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sign In Error", "You account in unavailable at the moment. Please contact SuperUser."));
            return "employeeSignIn";
        }
        signedIn = employee.getPassword().equals(password);
        if (signedIn) {
            return "menu";
        } else {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sign In Error", "Incorrect email or password, please try again."));
            return "employeeSignIn";
        }
    }

    public String signOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Signed Out", "Thank you, have a good day."));
        return "menu";
    }

}
