package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDaoImpl;
import com.bionic.edu.entity.Role;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

    @Override
    public Role findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Role WHERE name = :name");
        query.setParameter("name", name);
        return (Role) query.uniqueResult();
    }
}
