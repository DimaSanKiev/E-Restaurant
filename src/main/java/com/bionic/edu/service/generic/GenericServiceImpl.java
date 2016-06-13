package com.bionic.edu.service.generic;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.dao.generic.GenericDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenericServiceImpl<T> implements GenericService<T> {

    private GenericDao<T> dao;

//    public GenericServiceImpl() {
//    }
//
//    @Autowired
//    public GenericServiceImpl(GenericDaoImpl<T> dao) {
//        this.dao = dao;
//    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public T findById(int id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(T t) {
        dao.save(t);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(int id) {
        dao.delete(id);
    }
}
