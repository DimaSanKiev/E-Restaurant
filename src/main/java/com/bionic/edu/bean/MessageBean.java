package com.bionic.edu.bean;

import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named("msgs")
@Scope("request")
public class MessageBean implements Serializable {
    private static final long serialVersionUID = 8775004566511526824L;

    private final String nameRequired = "Customer's name field could not be empty";
    private final String emailRequired = "Customer's email field could not be empty";
    private final String passwordRequired = "Customer's password field could not be empty";
    private final String passwordRange = "Customer's password should not be less than 8 symbols";
    private final String addressRequired = "Customer's address field could not be empty";
    private final String birthDateConverter = "Customer's birthdate field has wrong date format";
    private final String roleRequired = "Employee's role is required";

    public MessageBean() {
    }

    public String getNameRequired() {
        return nameRequired;
    }

    public String getEmailRequired() {
        return emailRequired;
    }

    public String getPasswordRequired() {
        return passwordRequired;
    }

    public String getPasswordRange() {
        return passwordRange;
    }

    public String getAddressRequired() {
        return addressRequired;
    }

    public String getBirthDateConverter() {
        return birthDateConverter;
    }

    public String getRoleRequired() {
        return roleRequired;
    }

    public void showInfo(String head, String body) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(head, body));
    }
}
