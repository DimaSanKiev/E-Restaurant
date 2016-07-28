package com.bionic.edu.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Class for displaying pop-up notifications.
 */
public class GrowlMessenger {

    /**
     * Adds a pop-up message on a page.
     * @param header
     *        The header of message
     * @param detail
     *        The body text of message
     * @param severity
     *        {@code FacesMessage.Severity} level of the message:
     *        SEVERITY_INFO, SEVERITY_WARN, SEVERITY_ERROR, SEVERITY_FATAL
     */
    public static void addMessage(String header, String detail, FacesMessage.Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.getExternalContext().getFlash().setRedirect(true);
        context.addMessage(null, new FacesMessage(severity, header, detail));
    }
}
