package com.bionic.edu.service;

import com.bionic.edu.entity.Role;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RoleServiceImplTest {

    private RoleService roleService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");
        roleService = context.getBean(RoleService.class);
    }

    @Test
    public void testFindById() throws Exception {
        Role role = roleService.findById(1);
        assertNotNull(role);
        assertEquals(1, role.getId());
    }

    @Test
    public void testFindByName() throws Exception {
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findByName("SUPER_USER"));
        roles.add(roleService.findByName("ADMIN"));
        roles.add(roleService.findByName("KITCHEN_STAFF"));
        roles.add(roleService.findByName("DELIVERY_STAFF"));
        roles.add(roleService.findByName("BUSINESS_ANALYST"));
        assertNotNull(roles);
        assertEquals(5, roles.size());
    }

    @Test
    public void testFindAll() throws Exception {
        List<Role> roles = roleService.findAll();
        System.out.println("+++++++++");
        roles.forEach(System.out::println);
        assertNotNull(roles);
        assertEquals(5, roles.size());
    }

    @Test
    public void testSave_notNull() throws Exception {
        Role role = new Role("INSPECTOR");
        roleService.save(role);
        int id = role.getId();
        assertNotNull(roleService.findById(id));
        roleService.delete(id);
    }

    @Test
    public void testSave_listSize() throws Exception {
        List<Role> list1 = roleService.findAll();
        Role role = new Role("INSPECTOR");
        roleService.save(role);
        List<Role> list2 = roleService.findAll();
        assertEquals(list2.size() - list1.size(), 1);
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