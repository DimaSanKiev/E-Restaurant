package com.bionic.edu.bean;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.service.CustomerService;
import com.bionic.edu.util.AuthorizationFilter;
import com.bionic.edu.util.Crypto;
import com.bionic.edu.exception.CustomerBlockedException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
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
public class CustomerBean implements Serializable {
    private static final long serialVersionUID = -7781671161549205986L;

    private String email;
    private String password;
    private boolean signedIn;
    private List<Customer> customers = null;
    private Customer customer = null;
    @Inject
    private CustomerService customerService;

    private static final Logger logger = LogManager.getLogger(CustomerBean.class);

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
        try {
            customerService.save(customer);
        } catch (Exception e) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sign Up Error", "Current email is already used."));
            logger.error("Sign Up Error - Current email is already used.");
            return "signUp";
        }
        signIn(customer.getEmail(), customer.getPassword());
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Sign Up Success", "You have successfully registered on ERestaurant."));
        return "menu";
    }

    public String addCustomer() {
        customer = new Customer();
        return "newCustomer";
    }

    public String updateCustomer(String id) {
        customer = customerService.findById(Integer.valueOf(id));
        return "editCustomer";
    }

    public String deleteCustomer(String id) {
        customerService.delete(Integer.valueOf(id));
        return "customerList";
    }

    public String signIn(String email, String password) {
        String decryptPass = Crypto.encrypt(password);
        try {
            customer = customerService.signIn(email, decryptPass);
        } catch (NoResultException e) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sign In Error", "Incorrect email or password, please try again."));
            logger.error("Sign In Error - Incorrect email or password.");
            return "signIn";
        } catch (CustomerBlockedException e) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sign In Error", "Your account is blocked."));
            logger.error("Sign In Error - Account blocked.");
            return "signIn";
        }
        signedIn = customer.getPassword().equals(password);
        if (signedIn) {
            return "menu";
        } else {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sign In Error", "Incorrect email or password, please try again."));
            logger.error("Sign In Error - Incorrect email or password.");
            return "signIn";
        }
    }

    public String signOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Signed Out", "Thank you for visiting ERestaurant."));
        logger.info("Customer signed out.");
        return "menu";
    }

}
