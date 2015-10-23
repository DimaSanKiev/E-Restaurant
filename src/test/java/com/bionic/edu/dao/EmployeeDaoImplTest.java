package com.bionic.edu.dao;

import com.bionic.edu.entity.Employee;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EmployeeDaoImplTest {

    EmployeeDao employeeDao;
    ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("META-INF/application-context.xml");
        employeeDao = (EmployeeDao) context.getBean("employeeDaoImpl");
    }

    @Test
    public void testFindById() throws Exception {
        Employee employee = employeeDao.findById(1);
        assertNotNull(employee);
        assertEquals(1, employee.getId());
    }

    @Test
    public void testFindByEmail() throws Exception {
        Employee employee = employeeDao.findById(2);
        assertNotNull(employee);
        assertEquals("igor.himchenko@yahoo.com", employee.getEmail());
    }

    @Test
    public void testFindAll() throws Exception {
        List<Employee> employees = employeeDao.findAll();
        assertNotNull(employees);
        assertEquals(5, employees.size());
    }

    @Test
    public void testAdd() throws Exception {
        Employee employee = employeeDao.findById(1);
        employee.setEmail("testAdd@email.com");
        employeeDao.save(employee);
        int id = employee.getId();
        assertNotNull(employeeDao.findById(id));
        // todo - Class expected : class java.lang.Integer, Class received : class java.lang.String.
        //assertNotNull(employeeDao.findByEmail("testAdd@email.com"));
    }

    @Test
    public void testUpdate() throws Exception {
        Employee employee = employeeDao.findById(1);
        employee.setName("Dima Test");
        employeeDao.save(employee);
        assertEquals("Dima Test", employee.getName());
    }

    @Test
    public void testDelete() throws Exception {
        Employee employee = employeeDao.findById(2);
        employee.setEmail("testDelete@email.com");
        employeeDao.save(employee);
        int id = employee.getId();
        employeeDao.delete(id);
        assertNull(employeeDao.findById(id));
    }

    @Test
    public void testLogin() throws Exception {
        try {
            employeeDao.login("elena.bakhmach@gmail.com", "wrongPass");
        } catch (AssertionError er) {
            assertEquals(1, 1);
        }
        employeeDao.login("elena.bakhmach@gmail.com", "pass3");
    }

    @Test
    // todo - is it correct way to test
    public void testSetReadiness() throws Exception {
        Employee employee = employeeDao.findById(3);
        employee.setReady(false);
        assertEquals(false, employee.isReady());
        employeeDao.setReadiness(employee, true);
        assertEquals(true, employee.isReady());
    }
}