package com.bionic.edu.service;

import com.bionic.edu.entity.Customer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerServiceImplTest {

    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");
        customerService = context.getBean(CustomerService.class);
    }

    @Test
    public void findByIdNotNull() throws Exception {
        Customer customer = customerService.findById(1);
        System.out.println(customer);
        assertNotNull(customer);
        assertEquals(1, customer.getId());
    }

    @Test
    public void findByEmailNotNull() throws Exception {
        Customer customer = customerService.findByEmail("igor.shevchenko@yahoo.com");
        assertNotNull(customer);
        assertEquals("igor.shevchenko@yahoo.com", customer.getEmail());
    }

    @Test
    public void findAllListSize() throws Exception {
        List<Customer> customers = customerService.findAll();
        assertNotNull(customers);
        customers.forEach(System.out::println);
        assertEquals(5, customers.size());
    }

    @Test
    public void addingCustomerSetsId() throws Exception {
        Customer customer = createTestCustomer();
        int originalId = customer.getId();
        customerService.save(customer);
        assertNotEquals(originalId, customer.getId());
    }

    @Test
    public void addingCustomerIncreasesListSize() throws Exception {
        List<Customer> list1 = customerService.findAll();
        Customer customer = createTestCustomer();
        customerService.save(customer);
        List<Customer> list2 = customerService.findAll();
        assertEquals(1, list2.size() - list1.size());
    }

    @Test
    public void updateCustomerName() throws Exception {
        Customer customer = customerService.findById(1);
        String originalName = customer.getName();
        customer.setName("Olga Kovalenko");
        customerService.save(customer);
        assertNotEquals(originalName, customer.getName());
    }

    @Test
    public void deletedCustomerIsNull() throws Exception {
        Customer customer = createTestCustomer();
        customerService.save(customer);
        int id = customer.getId();
        customerService.delete(id);
        assertNull(customerService.findById(id));
    }

    @Test
    public void signInWithWrongCredentialsThrowsException() throws Exception {
        // TODO: 16.06.2016 - signing in with wrong credentials throws exception
    }

    private Customer createTestCustomer() {
        Customer customer = new Customer("Test Customer", "testAdd@email.com", "testPass", "testAddress", new Date(Calendar.getInstance().getTime().getTime()));
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd.HH:mm:ss");
        java.util.Date date = new java.util.Date();
        customer.setEmail("customer." + dateFormat.format(date) + "@erestaurant.com");
        return customer;
    }
}