package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDaoImpl;
import com.bionic.edu.entity.Orders;
import com.bionic.edu.util.ReportCategory;
import com.bionic.edu.util.ReportDish;
import com.bionic.edu.util.ReportTotal;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Orders> implements OrderDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Orders> getDeliveryListByTime() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Orders WHERE orderStatus.id = 3 OR orderStatus.id = 4 " +
                "ORDER BY dateTimeTaken ASC").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Orders> getDeliveryListByStatus() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Orders WHERE orderStatus.id = 3 OR orderStatus.id = 4 " +
                "ORDER BY orderStatus.id").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Orders> getCustomersOrder(int customerId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Orders WHERE customer.id = :customerId");
        query.setParameter("customerId", customerId);
        return query.list();
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<ReportTotal> getReportTotal(Date startPeriod, Date endPeriod) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT new com.bionic.edu.util.ReportTotal(" +
                "SUM(od.quantity), SUM(od.price), od.order.dateTimeTaken) " +
                "FROM OrderDishes od WHERE od.order.orderStatus.id = :status " +
                "AND od.order.dateTimeTaken BETWEEN :start AND :finish " +
                "GROUP BY od.order.dateTimeTaken ORDER BY od.order.dateTimeTaken");
        query.setParameter("status", 5);
        query.setParameter("start", startPeriod);
        query.setParameter("finish", endPeriod);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ReportCategory> getReportCategory(Date startPeriod, Date endPeriod) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT new com.bionic.edu.util.ReportCategory(" +
                "od.dish.category.name, COUNT(od.order.id), SUM(od.price)) " +
                "FROM OrderDishes od WHERE od.order.orderStatus.id = :status " +
                "AND od.order.dateTimeTaken BETWEEN :start AND :finish " +
                "GROUP BY od.dish.category.name ORDER BY od.dish.category.name");
        query.setParameter("status", 5);
        query.setParameter("start", startPeriod);
        query.setParameter("finish", endPeriod);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ReportDish> getReportDish(Date startPeriod, Date endPeriod) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT new com.bionic.edu.util.ReportDish(" +
                "od.dish.name, COUNT(od.dish.id), SUM(od.price)) " +
                "FROM OrderDishes od WHERE od.order.orderStatus.id = :status " +
                "AND od.order.dateTimeTaken BETWEEN :start AND :finish " +
                "GROUP BY od.dish.name ORDER BY od.dish.name");
        query.setParameter("status", 5);
        query.setParameter("start", startPeriod);
        query.setParameter("finish", endPeriod);
        return query.list();
    }
}
