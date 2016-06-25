package com.bionic.edu.bean;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.exception.BadCredentialsException;
import com.bionic.edu.exception.CustomerBlockedException;
import com.bionic.edu.service.CustomerService;
import com.bionic.edu.util.WeakCrypto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@Scope("session")
public class CustomerBean implements Serializable {
    private static final long serialVersionUID = -7781671161549205986L;

    private String email;
    private String password;
    private boolean signedIn;
    private boolean sortAscending;
    private List<Customer> customers = null;
    private Customer customer = null;
    @Inject
    private CustomerService customerService;

    private static final Logger logger = LogManager.getLogger(CustomerBean.class);

    @PostConstruct
    public void init() {
        customer = new Customer();
        customers = customerService.findAll();
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

    @SuppressWarnings("all")
    public void sortCustomers() {
        if (sortAscending) {
            // Ascending order
            Collections.sort(customers, (c1, c2) -> c1.getId() < c2.getId() ? -1 : 1);
            sortAscending = false;
        } else {
            // Descending order
            Collections.sort(customers, (c1, c2) -> c1.getId() > c2.getId() ? -1 : 1);
            sortAscending = true;
        }
    }

    public void refreshCustomerList() {
        customers = customerService.findAll();
    }

    public void blockUnblockCustomer(int id) {
        customerService.blockUnblockCustomer(id);
    }

    public String saveCustomer() {
        try {
            customerService.save(customer);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            addMessage("Saving Error", "Current email is already used. Please choose different one.", FacesMessage.SEVERITY_ERROR);
            logger.error("\nSaving customer ERROR - Current email is already used.", " CustomerID:" + customer.getId());
            return null;
        }
        showGrowlMessage("Saved successfully", "Customer's data was successfully saved.");
//        return "customerList";
        return null;
    }

    // TODO: 18.06.2016 - expand exceptions
    public String submitRegistration() {
        try {
            customerService.save(customer);
        } catch (Exception e) {
            addMessage("Sign Up Error", "Current email is already used.", FacesMessage.SEVERITY_ERROR);
            logger.error("\nSign Up ERROR - Current email is already used.", " CustomerID:" + customer.getId());
            return "signUp";
        }
        signIn(customer.getEmail(), customer.getPassword());
        addMessage("Sign Up Success", "You have successfully registered on ERestaurant.", FacesMessage.SEVERITY_INFO);
        logger.info("\nSign Up SUCCESS - Customer with CustomerID:" + customer.getId() + " signed up successfully.");
        signedIn = true;
        return "menu";
    }

    // TODO: 24.06.2016 - create a NEW customer instead of updating
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
        String decryptPass = WeakCrypto.encrypt(password);
        try {
            customer = customerService.signIn(email, decryptPass);
        } catch (BadCredentialsException e) {
            addMessage("Sign In Error", "Incorrect email or password, please try again.", FacesMessage.SEVERITY_ERROR);
            logger.error("\nCustomer sign in ERROR - Incorrect email or password." + "Email:" + email + " Password:" + password);
            customer = null;
            return "signIn";
        } catch (CustomerBlockedException e) {
            addMessage("Sign In Error", "Your account is blocked.", FacesMessage.SEVERITY_ERROR);
            logger.error("\nCustomer sign in ERROR - Account blocked." + " Email:" + email);
            customer = null;
            return "signIn";
        } catch (Exception ex) {
            addMessage("Sign In Error", "Unknown error.", FacesMessage.SEVERITY_ERROR);
            logger.error("\nCustomer sign in ERROR - Unknown error." + " Email:" + email + "\n" + ex.getMessage());
            customer = null;
            return "signIn";
        }
        signedIn = true;
        logger.info("\nCustomer " + customer.getEmail() + " signed in successfully");
        return "menu";
    }

    public String signOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        showGrowlMessage("Signed Out", "Thank you for visiting ERestaurant.");
        logger.info("Customer signed out.", "CustomerID:" + customer.getId() + "Email:" + customer.getEmail());
        return "menu";
    }

    private void addMessage(String header, String detail, FacesMessage.Severity severity) {
        RequestContext.getCurrentInstance().showMessageInDialog(
                new FacesMessage(severity, header, detail));
    }

    private void showGrowlMessage(String header, String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(header, detail));
    }

}
