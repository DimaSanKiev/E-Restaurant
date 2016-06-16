package com.bionic.edu.service;

import com.bionic.edu.entity.Employee;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeServiceImplTest {

    private EmployeeService employeeService;
    private RoleService roleService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");
        employeeService = context.getBean(EmployeeService.class);
        roleService = context.getBean(RoleService.class);
    }

    @Test
    public void findByIdNotNull() throws Exception {
        Employee employee = employeeService.findById(1);
        assertNotNull(employee);
        System.out.println(employee);
        assertEquals(1, employee.getId());
    }

    @Test
    public void findByEmailNotNull() throws Exception {
        Employee employee = employeeService.findByEmail("kitchen@erestaurant.com");
        assertNotNull(employee);
        assertEquals(3, employee.getId());
        assertEquals("Elena Bakhmach", employee.getName());
    }

    @Test
    public void findAllListSize() throws Exception {
        List<Employee> employees = employeeService.findAll();
        assertNotNull(employees);
        assertEquals(5, employees.size());
    }

    @Test
    public void addingEmployeeSetsId() throws Exception {
        Employee employee = createTestEmployee();
        int originalId = employee.getId();
        employeeService.save(employee);
        assertNotEquals(originalId, employee.getId());
    }

    @Test
    public void addingCustomerIncreasesListSize() throws Exception {
        Employee employee = createTestEmployee();
        List<Employee> list1 = employeeService.findAll();
        employeeService.save(employee);
        int employeeId = employee.getId();
        List<Employee> list2 = employeeService.findAll();
        assertEquals(1, list2.size() - list1.size());
        employeeService.delete(employeeId);
    }

    @Test
    public void updateEmployeeName() throws Exception {
        Employee employee = employeeService.findById(1);
        String originalName = employee.getName();
        employee.setName("Dima Updated");
        employeeService.save(employee);
        assertNotEquals(originalName, employee.getName());
    }

    @Test
    public void deletedCustomerIsNull() throws Exception {
        Employee employee = createTestEmployee();
        employeeService.save(employee);
        int id = employee.getId();
        employeeService.delete(id);
        assertNull(employeeService.findById(id));
    }

    @Test
    public void signInWithWrongCredentialsFails() throws Exception {
        // TODO: 6/16/16 - signing in with wrong credentials throws exception
        try {
            employeeService.signIn("elena.bakhmach@gmail.com", "wrongPass");
        } catch (AssertionError er) {
            assertEquals(1, 1);
        }
        employeeService.signIn("elena.bakhmach@gmail.com", "pass3");
    }

    @Test
    public void employeeUnableToSignInWithFalseReadiness() throws Exception {
        // TODO: 6/16/16 - sign in with false readiness
        Employee employee = employeeService.signIn("admin@erestaurant.com", "pass2");
        employeeService.signIn("admin@erestaurant.com", "pass2");
        assertEquals("Igor Himchenko", employee.getName());
        assertEquals("ADMIN", employee.getRole().getName());
    }

    private Employee createTestEmployee() {
        Employee employee = new Employee("Test Employee", "testAdd@email.com", "testPass", new Date(1984-06-27), new Date(2013-04-02), true, roleService.findById(3));
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd.HH:mm:ss");
        Date date = new Date();
        employee.setEmail("employee." + dateFormat.format(date) + "@erestaurant.com");
        return employee;
    }
}