package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.DaoInterface;
import com.bionic.edu.entity.Role;

public interface RoleDao extends DaoInterface<Role> {

    Role findByName(String name);
}
