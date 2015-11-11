package com.bionic.edu.bean;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.service.CustomerService;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Scope("request")
public class CustomerBean {
    private List<Customer> customers = null;
    private Customer customer = null;
    @Inject
    private CustomerService customerService;

    public CustomerBean() {
        customer = new Customer();
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
        return "CustomerList";
    }

    public String addCustomer(){
        customer = new Customer();
        return "NewCustomer";
    }
}
