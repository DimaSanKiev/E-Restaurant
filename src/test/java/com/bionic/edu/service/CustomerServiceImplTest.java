package com.bionic.edu.service;

import com.bionic.edu.dao.CustomerDao;
import com.bionic.edu.entity.Customer;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomerServiceImplTest extends TestCase {

    CustomerService customerService;
    ApplicationContext context;

    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("META-INF/application-context.xml");
        customerService = (CustomerService) context.getBean("customerServiceImpl");
    }

    public void testFindById() throws Exception {
        Customer customer = customerService.findById(1);
        assertNotNull(customer);
        assertEquals(1, customer.getId());
    }

    public void testFindByEmail() throws Exception {

    }

    public void testFindAll() throws Exception {

    }

    public void testAdd() throws Exception {

    }

    public void testUpdate() throws Exception {

    }

    public void testDelete() throws Exception {

    }

    public void testLogin() throws Exception {

    }
}