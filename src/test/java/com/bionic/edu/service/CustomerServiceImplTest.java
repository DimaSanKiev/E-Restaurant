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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomerServiceImplTest {

    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");
        customerService = context.getBean(CustomerService.class);
    }

    @Test
    public void testFindById() throws Exception {
        Customer customer = customerService.findById(1);
        System.out.println(customer);
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
        customers.forEach(System.out::println);
        assertEquals(5, customers.size());
    }

    @Test
    public void testSave_notNull() throws Exception {
        Customer customer = createTestCustomer();
        customerService.save(customer);
        int id = customer.getId();
        assertNotNull(customerService.findById(id));
    }

    @Test
    public void testSave_listSize() throws Exception {
        List<Customer> list1 = customerService.findAll();
        Customer customer = createTestCustomer();
        customerService.save(customer);
        List<Customer> list2 = customerService.findAll();
        assertEquals(1, list2.size() - list1.size());
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
        Customer customer = createTestCustomer();
        customerService.save(customer);
        int id = customer.getId();
        customerService.delete(id);
        assertEquals(null, customerService.findById(id));
    }

    @Test
    public void testSignIn() throws Exception {
        Customer customer = customerService.signIn("kate.belova@gmail.com", "wrongPass");
        customerService.signIn("kate.belova@gmail.com", "pass3");
        assertEquals("Kate Belova", customer.getName());
    }

    private Customer createTestCustomer() {
        Customer customer = new Customer("Test Customer", "testAdd@email.com", "testPass", "testAddress", new Date(Calendar.getInstance().getTime().getTime()));
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd.HH:mm:ss");
        java.util.Date date = new java.util.Date();
        customer.setEmail("customer." + dateFormat.format(date) + "@erestaurant.com");
        return customer;
    }
}