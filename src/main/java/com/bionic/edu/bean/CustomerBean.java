package com.bionic.edu.bean;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.service.CustomerService;
import org.springframework.context.annotation.Scope;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Scope("session")
public class CustomerBean {
    private String email;
    private String password;
    private boolean signedIn;
    private String message = "";
    private List<Customer> customers = null;
    private Customer customer = null;
    @Inject
    private CustomerService customerService;

    public CustomerBean() {
        customer = new Customer();
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

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }


    public void refreshList() {
        customers = customerService.findAll();
    }

    public String saveCustomer() {
        customerService.save(customer);
        return "customerList";
    }

    public String submitRegistration() {
        customerService.save(customer);
        message = "Registration successful.";
        return "menu";
    }

    public String addCustomer() {
        customer = new Customer();
        return "newCustomer";
    }

    public String updateCustomer(String id) {
        customer = customerService.findById(Integer.valueOf(id));
        return "newCustomer";
    }

    public String deleteCustomer(String id) {
        customerService.delete(Integer.valueOf(id));
        return "customerList";
    }

    public String signIn(String email, String password) {
        try {
            customerService.login(email, password);
        } catch (Exception e) {
            message = "Incorrect email or password, please try again.";
            return "signIn";
        }
        signedIn = true;
        message = "Hello, " + customerService.findByEmail(email).getName() + "!";
        return "menu";
    }

    public String signOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        message = "Thank you for visiting ERestaurant, see you again.";
        return "menu";
    }

    public String printMessage() {
        String temp = message;
        message = "";
        return temp;
    }

}
