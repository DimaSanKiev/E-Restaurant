package com.bionic.edu.service;

import com.bionic.edu.dao.RoleDao;
import com.bionic.edu.entity.Role;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class RoleServiceImpl implements RoleService {

    @Inject
    private RoleDao roleDao;

    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }

    @Transactional
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Transactional
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public void delete(int id) {
        roleDao.delete(id);
    }
}
