package com.bionic.edu.dao;

import com.bionic.edu.entity.*;
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
    public void add(Orders order) {
        em.persist(order);
    }

    @Override
    public void update(Orders order) {
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
                        "ORDER BY o.dateTimeTaken", Orders.class);
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
    public void setOrderStatus(Orders order, int statusId) {
        TypedQuery<Orders> query = em.createQuery(
                "UPDATE Orders o SET o.orderStatus = :order_status_id " +
                        "WHERE o.id = :order_id", Orders.class);
        query.setParameter("order_status_id", order.getId()).
                setParameter("order_status_id", statusId).executeUpdate();
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
}
