package com.bionic.edu.service;

import com.bionic.edu.entity.Role;
import com.bionic.edu.service.generic.ServiceInterface;

public interface RoleService extends ServiceInterface<Role> {

    Role findByName(String name);
}
