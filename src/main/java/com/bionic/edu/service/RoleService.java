package com.bionic.edu.service;

import com.bionic.edu.entity.Role;

import java.util.List;

public interface RoleService {

    Role findById(int id);

    List<Role> findAll();

    void save(Role role);

    void delete(int id);
}
