package com.bionic.edu.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class GrowlMessenger {

    public static void addMessage(String header, String detail, FacesMessage.Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.getExternalContext().getFlash().setRedirect(true);
        context.addMessage(null, new FacesMessage(severity, header, detail));
    }
}
