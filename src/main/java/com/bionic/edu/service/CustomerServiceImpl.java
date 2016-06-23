package com.bionic.edu.service;

import com.bionic.edu.dao.CustomerDao;
import com.bionic.edu.entity.Customer;
import com.bionic.edu.exception.BadCredentialsException;
import com.bionic.edu.exception.CustomerBlockedException;
import com.bionic.edu.util.WeakCrypto;
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
        Customer customer = customerDao.findById(id);
        if (customer != null)
            customer.setPassword(WeakCrypto.encrypt(customer.getPassword()));
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = customerDao.findAll();
        for (Customer customer : customers) {
            customer.setPassword(WeakCrypto.encrypt(customer.getPassword()));
        }
        return customers;
    }

    @Transactional
    @Override
    public void save(Customer customer) {
        customer.setPassword(WeakCrypto.encrypt(customer.getPassword()));
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
    }

    @Override
    public Customer findByEmail(String email) {
        Customer customer = customerDao.findByEmail(email);
//        customer.setPassword(WeakCrypto.encrypt(customer.getPassword()));
        return customer;
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
