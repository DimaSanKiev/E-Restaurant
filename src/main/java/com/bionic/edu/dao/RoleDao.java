package com.bionic.edu.dao;

import com.bionic.edu.entity.Role;

import java.util.List;

public interface RoleDao {

    Role findById(int id);

    Role findByName(String name);

    List<Role> findAll();

    void save(Role role);

    void delete(int id);
}
