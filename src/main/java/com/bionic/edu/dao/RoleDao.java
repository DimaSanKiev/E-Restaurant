package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.Role;

public interface RoleDao extends GenericDao<Role> {

    Role findByName(String name);
}
