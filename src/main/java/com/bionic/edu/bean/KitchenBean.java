package com.bionic.edu.bean;

import com.bionic.edu.entity.OrderDishes;
import com.bionic.edu.service.OrderDishesService;
import com.bionic.edu.service.OrderService;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@Scope("session")
public class KitchenBean implements Serializable {
    private static final long serialVersionUID = -6595295070802930845L;

    private List<OrderDishes> orderDishesList;
    @Inject
    private OrderDishesService orderDishesService;
    @Inject
    private OrderService orderService;

    public KitchenBean() {
        orderDishesList = new ArrayList<>();
    }

    public List<OrderDishes> getOrderDishesList() {
        return orderDishesList;
    }

    public void setOrderDishesList(List<OrderDishes> orderDishesList) {
        this.orderDishesList = orderDishesList;
    }

    public void refreshList() {
        orderDishesList = orderDishesService.getKitchenPendingList();
    }

    public String markDone(int orderDishId) {
        OrderDishes orderDish = orderDishesService.findById(orderDishId);
        orderDish.setReadiness(true);
        orderDishesService.save(orderDish);
        orderDishesService.checkIfOrderReady(orderService.findById(orderDishesService.findById(orderDishId).getOrder().getId()));
//        orderDishesService.markDone(Integer.valueOf(orderDishId));
        return "kitchen";
    }

}
