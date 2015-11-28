package com.bionic.edu.dao;

import com.bionic.edu.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Role findById(int id) {
        return em.find(Role.class, id);
    }

    @Override
    public Role findByName(String name) {
        TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                .setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<Role> findAll() {
        TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r", Role.class);
        return query.getResultList();
    }

    @Override
    public void save(Role role) {
        if (role.getId() == 0) {
            em.persist(role);
        } else
            em.merge(role);
    }

    @Override
    public void delete(int id) {
        Role role = em.find(Role.class, id);
        if (role != null) {
            em.remove(role);
        }
    }
}
