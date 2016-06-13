package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.OrderDishes;

import java.util.List;

public interface OrderDishesDao extends GenericDao<OrderDishes> {

    List<OrderDishes> getAllDishesFromOrder(int orderId);

    List<OrderDishes> getUndoneDishesFromOrder(int orderId);

    List<OrderDishes> getKitchenPendingList();
}
