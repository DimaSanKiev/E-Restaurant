package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.DaoInterface;
import com.bionic.edu.entity.OrderDishes;

import java.util.List;

public interface OrderDishesDao extends DaoInterface<OrderDishes> {

    List<OrderDishes> getAllDishesFromOrder(int orderId);

    List<OrderDishes> getUndoneDishesFromOrder(int orderId);

    List<OrderDishes> getKitchenPendingList();
}
