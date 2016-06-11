package com.bionic.edu.dao.generic;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDao<T> implements DaoInterface<T> {

    @SuppressWarnings("unchecked")
    private final Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @SuppressWarnings("unchecked")
    public GenericDao() {
        Class<T> domainClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericDao.class);
    }

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public T findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.get(persistentClass, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(persistentClass);
        return (List<T>) criteria.list();
    }

    @Override
    public void save(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(t);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        T t = (T) session.load(persistentClass, id);
        session.delete(t);
    }
}
