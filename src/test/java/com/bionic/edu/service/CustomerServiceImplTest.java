package com.bionic.edu.service;

import com.bionic.edu.entity.Customer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomerServiceImplTest {
    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/application-context.xml");
        customerService = context.getBean(CustomerService.class);
    }

    @Test
    public void testFindById() throws Exception {
        Customer customer = customerService.findById(1);
        assertNotNull(customer);
        assertEquals(1, customer.getId());
    }

    @Test
    public void testFindByEmail() throws Exception {
        Customer customer = customerService.findByEmail("igor.shevchenko@yahoo.com");
        assertNotNull(customer);
        assertEquals("igor.shevchenko@yahoo.com", customer.getEmail());
    }

    @Test
    public void testFindAll() throws Exception {
        List<Customer> customers = customerService.findAll();
        assertNotNull(customers);
        assertEquals(5, customers.size());
    }

    @Test
    public void testSave() throws Exception {
        Customer customer = new Customer("testAdd", "testAdd@email.com", "testPass", "testAddress", new Date(Calendar.getInstance().getTime().getTime()));
        customerService.save(customer);
        assertNotNull(customerService.findByEmail("testAdd@email.com"));
    }

    @Test
    public void testUpdate() throws Exception {
        Customer customer = customerService.findById(1);
        customer.setName("Olga Kovalenko");
        customerService.save(customer);
        assertEquals("Olga Kovalenko", customer.getName());
    }

    @Test
    public void testDelete() throws Exception {
        Customer customer = new Customer("testDelete", "testDelete@email.com", "testPass", "testAddress", new Date(Calendar.getInstance().getTime().getTime()));
        customerService.save(customer);
        int id = customer.getId();
        customerService.delete(id);
        assertEquals(null, customerService.findById(id));
    }

    @Test
    public void testLogin() throws Exception {
        try {
            customerService.login("kate.belova@gmail.com", "wrongPass");
        } catch (Throwable e) {
            assertEquals(1, 1);
        }
        customerService.login("kate.belova@gmail.com", "pass3");

    }
}