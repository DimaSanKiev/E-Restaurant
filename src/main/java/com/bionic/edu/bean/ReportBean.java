package com.bionic.edu.bean;

import com.bionic.edu.service.OrderService;
import com.bionic.edu.util.ReportCategory;
import com.bionic.edu.util.ReportTotal;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Named
@Scope("session")
public class ReportBean {
    @Inject
    private OrderService orderService;
    private List<ReportCategory> reportCategories = null;
    private List<ReportTotal> reportTotals = null;
    private Date startDate;
    private Date endDate;

    public List<ReportCategory> getReportCategories() {
        return reportCategories;
    }

    public void setReportCategories(List<ReportCategory> reportCategories) {
        this.reportCategories = reportCategories;
    }

    public List<ReportTotal> getReportTotals() {
        return reportTotals;
    }

    public void setReportTotals(List<ReportTotal> reportTotals) {
        this.reportTotals = reportTotals;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void refreshCategoryReport() {
        reportCategories = orderService.getReportCategory(
                new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()));
    }

    public void refreshTotalReport() {
        reportTotals = orderService.getReportTotal(
                new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()));
    }


    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
}
