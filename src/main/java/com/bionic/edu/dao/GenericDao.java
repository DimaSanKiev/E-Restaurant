package com.bionic.edu.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDao<T> implements DaoInterface<T> {

    @SuppressWarnings("unchecked")
    private final Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public T findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(persistentClass, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(persistentClass);
        return (List<T>) criteria.list();
    }

    // todo
    @Override
    public void save(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(t);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        T t = session.load(persistentClass, id);
        session.delete(t);
    }
}
