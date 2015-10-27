package com.bionic.edu.service;

import com.bionic.edu.entity.Role;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RoleServiceImplTest {

    RoleService roleService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/application-context.xml");
        roleService = context.getBean(RoleService.class);
    }

    @Test
    public void testFindById() throws Exception {
        Role role = roleService.findById(1);
        assertNotNull(role);
        assertEquals(1, role.getId());
    }

    @Test
    public void testFindAll() throws Exception {
        List<Role> roles = roleService.findAll();
        assertNotNull(roles);
        assertEquals(5, roles.size());
    }

    @Test
    public void testSave() throws Exception {
        Role role = new Role("INSPECTOR");
        roleService.save(role);
        int id = role.getId();
        assertNotNull(roleService.findById(id));
        roleService.delete(id);
    }

    @Test
    public void testDelete() throws Exception {
        Role role = new Role("AUDITOR");
        roleService.save(role);
        int id = role.getId();
        roleService.delete(id);
        assertNull(roleService.findById(id));
    }
}