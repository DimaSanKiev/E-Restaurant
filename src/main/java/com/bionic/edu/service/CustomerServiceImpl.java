package com.bionic.edu.service;

import com.bionic.edu.dao.CustomerDao;
import com.bionic.edu.entity.Customer;
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
        return customerDao.findById(id);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerDao.findByEmail(email);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public void save(Customer customer) {
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
