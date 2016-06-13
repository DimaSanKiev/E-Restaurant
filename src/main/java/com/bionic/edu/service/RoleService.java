package com.bionic.edu.service;

import com.bionic.edu.entity.Role;
import com.bionic.edu.service.generic.GenericService;

public interface RoleService extends GenericService<Role> {

    Role findByName(String name);
}
