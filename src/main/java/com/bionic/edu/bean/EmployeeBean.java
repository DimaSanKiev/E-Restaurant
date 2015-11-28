package com.bionic.edu.bean;

import com.bionic.edu.entity.Employee;
import com.bionic.edu.entity.Role;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@Scope("session")
public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = -6003880259950580877L;

    private String email;
    private String password;
    private String role;
    private boolean signedIn;
    private Map<String, String> idNameRoleMap;
    private Map<String, Role> idRoleMap;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    public Map<String, String> getIdNameRoleMap() {
        return idNameRoleMap;
    }

    public void setIdNameRoleMap(Map<String, String> idNameRoleMap) {
        this.idNameRoleMap = idNameRoleMap;
    }

    public Map<String, Role> getIdRoleMap() {
        return idRoleMap;
    }

    public void setIdRoleMap(Map<String, Role> idRoleMap) {
        this.idRoleMap = idRoleMap;
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
        employee.setRole(idRoleMap.get(role));
        employeeService.save(employee);
        return "employeeList";
    }

    public String addEmployee() {
        refreshRoles();
        employee = new Employee();
        return "newEmployee";
    }

    public String updateEmployee(String id) {
        refreshRoles();
        employee = employeeService.findById(Integer.valueOf(id));
        return "newEmployee";
    }

    public void refreshRoles() {
        idNameRoleMap = new HashMap<>();
        idRoleMap = new HashMap<>();
        List<Role> dishCategories = roleService.findAll();
        for (Role dc : dishCategories) {
            idNameRoleMap.put(dc.getName(), String.valueOf(dc.getId()));
            idRoleMap.put(String.valueOf(dc.getId()), dc);
        }
    }

    public String signIn(String email, String password) {
        String decryptPass = Crypto.encrypt(password);
        try {
            employee = employeeService.signIn(email, decryptPass);
        } catch (NoResultException e) {
//             logger
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
            if (employee.getRole().equals(roleService.findByName("SUPER_USER")))
                return "superPanel";
            if (employee.getRole().equals(roleService.findByName("ADMIN")))
                return "adminPanel";
            if (employee.getRole().equals(roleService.findByName("KITCHEN_STAFF")))
                return "kitchen";
            if (employee.getRole().equals(roleService.findByName("DELIVERY_STAFF")))
                return "delivery";
            if (employee.getRole().equals(roleService.findByName("BUSINESS_ANALYST")))
                return "reports";
            return null;
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
