package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.Role;

public interface RoleDao extends GenericDao<Role> {

    /**
     * Finds employee's role by its name.
     *
     * @param name the name of a role
     * @return {@link Role} entity or {@literal null} if none found
     */
    Role findByName(String name);
}
