package com.bionic.edu.service;

import com.bionic.edu.dao.CustomerDao;
import com.bionic.edu.entity.Customer;
import com.bionic.edu.util.Crypto;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerDao customerDao;

    @Override
    public Customer findById(int id) {
        Customer customer = customerDao.findById(id);
        customer.setPassword(Crypto.encrypt(customer.getPassword()));
        return customer;
    }

    @Override
    public Customer findByEmail(String email) {
        Customer customer = customerDao.findByEmail(email);
        customer.setPassword(Crypto.encrypt(customer.getPassword()));
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = customerDao.findAll();
        for (Customer customer : customers) {
            customer.setPassword(Crypto.encrypt(customer.getPassword()));
        }
        return customers;
    }

    @Transactional
    @Override
    public void save(Customer customer) {
        customer.setPassword(Crypto.encrypt(customer.getPassword()));
        customerDao.save(customer);
    }

    @Transactional
    @Override
    public void delete(int id) {
        customerDao.delete(id);
    }


    @Override
    public Customer login(String email, String password) {
        return customerDao.login(email, password);
    }

}
