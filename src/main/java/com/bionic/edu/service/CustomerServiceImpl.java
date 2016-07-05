package com.bionic.edu.service;

import com.bionic.edu.dao.CustomerDao;
import com.bionic.edu.entity.Customer;
import com.bionic.edu.exception.BadCredentialsException;
import com.bionic.edu.exception.CustomerBlockedException;
import com.bionic.edu.util.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {

    @Autowired
//    @Qualifier("customerDaoImpl")
    private CustomerDao customerDao;

//    public CustomerServiceImpl(){
//    }
//
//    @Autowired
//    public CustomerServiceImpl(GenericDaoImpl<Customer> genericDao){
//        super(genericDao);
//        this.customerDao = (CustomerDao) genericDao;
//    }

    @Override
    public Customer findById(int id) {
        return customerDao.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
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


    @Transactional
    @Override
    public void blockUnblockCustomer(int customerId) {
        Customer customer = customerDao.findById(customerId);
        customer.setBlocked(!customer.isBlocked());
        customerDao.save(customer);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerDao.findByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public Customer signIn(String email, String password) throws BadCredentialsException, CustomerBlockedException {
        Customer customer = customerDao.findByEmail(email);
        if (customer == null || !customer.getPassword().equals(password)) {
            throw new BadCredentialsException("Incorrect email or password.");
        } else if (customer.isBlocked()) {
            throw new CustomerBlockedException("Customer is blocked.");
        }
        return customer;
    }

}
