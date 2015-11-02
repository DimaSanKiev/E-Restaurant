package com.bionic.edu.bean;

import com.bionic.edu.entity.Customer;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named
@Scope("session")
public class CustomerBean {
    private Customer customer;
    private String login;
    private String password;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String register() {
        return "registration.xhtml";
    }
}
