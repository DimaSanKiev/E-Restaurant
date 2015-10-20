package com.bionic.edu.dao;

import com.bionic.edu.entity.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomerDaoImplTest {

    CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/application-context.xml");
        customerDao = (CustomerDao) context.getBean("customerDaoImpl");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFindById_1() throws Exception {
        Customer customer = customerDao.findById(1);
        assertNotNull(customer);
        assertEquals(1, customer.getId());
    }

    @Test
    public void testFindByEmail() throws Exception {
        Customer customer = customerDao.findById(2);
        assertNotNull(customer);
        assertEquals("igor.shevchenko@yahoo.com", customer.getEmail());
    }

    @Test
    public void testFindAll() throws Exception {
        List<Customer> customers = customerDao.findAll();
        assertNotNull(customers);
        assertEquals(5, customers.size());
    }

    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testRegister() throws Exception {

    }

    @Test
    public void testLogin() throws Exception {

    }
}