package com.bionic.edu.dao;

import com.bionic.edu.entity.*;
import com.bionic.edu.util.Report;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Orders findById(int id) {
        Orders order;
        order = em.find(Orders.class, id);
        return order;
    }

    @Override
    public List<Orders> findAll() {
        TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o", Orders.class);
        return query.getResultList();
    }

    @Override
    public void save(Orders order) {
        if (order.getId() == 0) {
            em.persist(order);
        } else
            em.merge(order);
    }

    @Override
    public void delete(int id) {
        Orders order = em.find(Orders.class, id);
        if (order != null) {
            em.remove(order);
        }
    }


    @Override
    public List<Orders> getDeliveryListByTime() {
        TypedQuery<Orders> query = em.createQuery(
                "SELECT o FROM Orders o WHERE o.orderStatus.id = 2 " +
                        "ORDER BY o.dateTimeTaken DESC", Orders.class);
        return query.getResultList();
    }

    @Override
    public List<Orders> getDeliveryListByStatus() {
        TypedQuery<Orders> query = em.createQuery(
                "SELECT o FROM Orders o WHERE o.orderStatus.id = 2 " +
                        "ORDER BY o.orderStatus.id", Orders.class);
        return query.getResultList();
    }

    @Override
    public List<Orders> getCustomersOrder(int customerId) {
        TypedQuery<Orders> query = em.createQuery(
                "SELECT o FROM Orders o WHERE o.customer.id = :customerId", Orders.class).
                setParameter("customerId", customerId);
        return query.getResultList();
    }

    @Override
    public List<Report> getReport(Date startPeriod, Date endPeriod) {
        TypedQuery<Report> query = em.createQuery("SELECT new com.bionic.edu.util.Report(" +
                "COUNT(od.order), SUM(od.quantity * d.price), FUNC('DATE', od.order.dateTimeTaken)) " +
                "FROM order_dishes od, Dish d " +
                "WHERE od.dish.id = d.id " + // remove
                "AND FUNC('DATE', od.order.dateTimeTaken) BETWEEN :start AND :finish " +
                "GROUP BY FUNC('DATE', od.order.dateTimeTaken)", Report.class);
        query.setParameter("start", startPeriod);
        query.setParameter("finish", endPeriod);
        return query.getResultList();
    }

    @Override
    public List<Report> getReport(Date startPeriod, Date endPeriod, String category) {
        TypedQuery<Report> query = em.createQuery("SELECT new com.bionic.edu.util.Report(" +
                "SUM(od.quantity), SUM(d.price * od.quantity), FUNC('DATE',od.order.dateTimeTaken), " +
                "\"" + category + "\") " +
                "FROM order_dishes od, Dish d where od.order.dateTimeTaken between :start and :finish " +
                "and od.dish.category = \"" + category + "\"" +
                "GROUP BY FUNC('DATE',od.order.dateTimeTaken) " +
                "ORDER BY FUNC('DATE',od.order.dateTimeTaken)", Report.class);
        query.setParameter("start", startPeriod);
        query.setParameter("finish", endPeriod);
        return query.getResultList();
    }
}
