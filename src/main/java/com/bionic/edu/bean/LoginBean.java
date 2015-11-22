package com.bionic.edu.bean;

import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.io.Serializable;

@Named
@Scope("request")
public class LoginBean implements Serializable {
    private static final long serialVersionUID = -1575215658600923333L;

    private String email;
    private String password;
    private boolean employee;

    public LoginBean() {
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

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }
}
