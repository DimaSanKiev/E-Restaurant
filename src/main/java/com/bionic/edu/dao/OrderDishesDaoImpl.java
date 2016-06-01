package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.OrderDishes;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDishesDaoImpl extends GenericDao<OrderDishes> implements OrderDishesDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderDishes> getAllDishesFromOrder(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM OrderDishes od WHERE od.order.id = :id");
        query.setParameter("id", orderId);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderDishes> getUndoneDishesFromOrder(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM OrderDishes od WHERE od.order.id = :id AND od.readiness = FALSE");
        query.setParameter("id", orderId);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OrderDishes> getKitchenPendingList() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM OrderDishes od WHERE " +
                "od.dish.kitchenmade = TRUE AND od.readiness = FALSE " +
                "ORDER BY od.order.dateTimeTaken ASC").list();
    }
}
