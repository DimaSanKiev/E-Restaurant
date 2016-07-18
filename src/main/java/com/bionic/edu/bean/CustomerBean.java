package com.bionic.edu.bean;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.exception.BadCredentialsException;
import com.bionic.edu.exception.CustomerBlockedException;
import com.bionic.edu.exception.EmailUsedException;
import com.bionic.edu.service.CustomerService;
import com.bionic.edu.util.Crypto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static com.bionic.edu.util.GlowlMessenger.addMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

@Named
@Scope("session")
public class CustomerBean implements Serializable {
    private static final long serialVersionUID = -7781671161549205986L;

    private String email;
    private String password;
    private String currentPassword = null;
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

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
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

    public String confirmChanges() {
        RequestContext context = RequestContext.getCurrentInstance();
        boolean confirmed;
        if (Crypto.encrypt(currentPassword).equals(customer.getPassword())) {
            try {
                saveCustomer();
            } catch (EmailUsedException e) {
                FacesContext.getCurrentInstance().validationFailed();
                return null;
            }
            confirmed = true;
            logger.warn("\nCustomer updating SUCCESS. CustomerID:" + customer.getId());
        } else {
            confirmed = false;
            FacesContext.getCurrentInstance().validationFailed();
            addMessage("Updating Error", "Your password is wrong, please try again.", SEVERITY_ERROR);
        }
        context.addCallbackParam("confirmed", confirmed);
        currentPassword = null;
        return null;
    }

    public String saveCustomer() throws EmailUsedException {
        try {
            customerService.update(customer);
        } catch (org.springframework.dao.DataIntegrityViolationException | javax.persistence.PersistenceException ex) {
            addMessage("Saving Error", "Current email is already used. Please choose different one.", SEVERITY_ERROR);
            logger.error("\nSaving customer ERROR - Current email is already used." + " CustomerID:" + customer.getId());
            throw new EmailUsedException("This email is already used.");
        }
            logger.warn("\nSaving customer SUCCESS." + " CustomerID:" + customer.getId());
        addMessage("Saved successfully", "Customer's data was successfully saved.", SEVERITY_INFO);
        return "menu";
    }

    public String submitRegistration() {
        String password = customer.getPassword();
        try {
            customerService.save(customer);
        } catch (Exception e) {
            addMessage("Sign Up Error", "Current email is already used.", SEVERITY_ERROR);
            logger.error("\nSign Up ERROR - Current email is already used.", " CustomerID:" + customer.getId());
            return "signUp";
        }
        signIn(customer.getEmail(), password);
        addMessage("Sign Up Success", "You have successfully registered on ERestaurant.", SEVERITY_INFO);
        logger.warn("\nSign Up SUCCESS - Customer with CustomerID:" + customer.getId() + " signed up successfully.");
        signedIn = true;
        return "menu";
    }

    public String deleteCustomer(String id) {
        customerService.delete(Integer.valueOf(id));
        return "customerList";
    }

    public String signIn(String email, String password) {
        String decryptPass = Crypto.encrypt(password);
        try {
            customer = customerService.signIn(email, decryptPass);
        } catch (BadCredentialsException e) {
            addMessage("Sign In Error", "Incorrect email or password, please try again.", SEVERITY_ERROR);
            logger.error("\nCustomer sign in ERROR - Incorrect email or password." + " Typed email:" + email + " Typed password:" + password);
            customer = null;
            return "signIn";
        } catch (CustomerBlockedException e) {
            addMessage("Sign In Error", "Your account is blocked.", SEVERITY_ERROR);
            logger.error("\nCustomer sign in ERROR - Account blocked." + " Email:" + email);
            customer = null;
            return "signIn";
        } catch (Exception ex) {
            addMessage("Sign In Error", "Unknown error.", SEVERITY_ERROR);
            logger.error("\nCustomer sign in ERROR - Unknown error." + " Email:" + email + "\n" + ex.getMessage());
            customer = null;
            return "signIn";
        }
        signedIn = true;
        logger.warn("\nCustomer " + customer.getEmail() + " signed in successfully");
        return "menu";
    }

    public String signOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        addMessage("Signed Out", "Thank you for visiting ERestaurant.", SEVERITY_INFO);
        logger.warn("\nCustomer signed out.", "CustomerID:" + customer.getId() + "Email:" + customer.getEmail());
        return "menu";
    }

}
