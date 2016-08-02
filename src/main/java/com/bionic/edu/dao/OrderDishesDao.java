package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.OrderDishes;

import java.util.List;

public interface OrderDishesDao extends GenericDao<OrderDishes> {

    /**
     * Finds all dishes in the specified order.
     *
     * @param orderId id of an order
     * @return list of {@link OrderDishes} or {@literal null} if none found
     */
    List<OrderDishes> getAllDishesFromOrder(int orderId);

    /**
     * Finds all dishes that are not ready and non-kitchen-done
     * in the specified order.
     *
     * @param orderId id of an order
     * @return list of {@link OrderDishes} or {@literal null} if none found
     */
    List<OrderDishes> getUndoneDishesFromOrder(int orderId);

    /**
     * Returns kitchen pending list for kitchen staff.
     * This list contains all ordered kitchen-made dishes that are not currently
     * ready. List is sorted by ascending time of taking order.
     *
     * @return list of {@link OrderDishes} or {@literal null} if none found
     */
    List<OrderDishes> getKitchenPendingList();
}
