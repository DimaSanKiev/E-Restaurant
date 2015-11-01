package com.bionic.edu.dao;

import com.bionic.edu.entity.*;
import com.bionic.edu.util.Report;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    // todo check!
    @Override
    public void submitByCustomer(Customer customer, Map<Dish, Integer> dishAmount) {
        Orders order = new Orders(new Timestamp(Calendar.getInstance().getTime().getTime()), customer);
        em.persist(order);
        for (Dish dish : dishAmount.keySet()) {
            for (int i = 1; i < dishAmount.get(dish); i++) {
                if (dish.isKitchenmade()) {
                    em.persist(new OrderDishes(dish.getPrice(), dish, order));
                    order.setOrderStatus(new OrderStatus("NOT_READY"));
                } else {
                    order.setOrderStatus(new OrderStatus("READY_FOR_SHIPMENT"));
                    em.persist(new OrderDishes(dish.getPrice(), dish, order));
                }
            }
        }
        em.merge(order);
    }

    @Override
    public List<Report> getReport(Timestamp startPeriod, Timestamp endPeriod) {
        TypedQuery<Report> query = em.createQuery(
                "SELECT new com.bionic.edu.util.Report(COUNT(o), SUM(o.totalPrice), FUNC('DATE', o.dateTimeTaken)) " +
                        "FROM Orders o WHERE o.dateTimeTaken BETWEEN ?1 AND ?2 " +
                        "GROUP BY FUNC('DATE', o.dateTimeTaken) " +
                        "ORDER BY FUNC('DATE', o.dateTimeTaken)", Report.class);
        query.setParameter(1, startPeriod);
        query.setParameter(2, endPeriod);
        return query.getResultList();
    }

    @Override
    public List<Report> getReport(Timestamp startPeriod, Timestamp endPeriod, String category) {
        TypedQuery<Report> query = em.createQuery(
                "SELECT new com.bionic.edu.util.Report (SUM(od.quantity), SUM(od.price * od.quantity), FUNC('DATE',od.order.dateTimeTaken), " +
                        "\"" + category + "\") " +
                        " FROM order_dishes od where od.order.dateTimeTaken between ?1 and ?2" +
                        " and od.dish.category = \"" + category + "\"" +
                        " GROUP BY FUNC('DATE',od.order.dateTimeTaken) " +
                        " ORDER BY FUNC('DATE',od.order.dateTimeTaken)", Report.class);
        query.setParameter(1, startPeriod);
        query.setParameter(2, endPeriod);
        return query.getResultList();
    }
}
