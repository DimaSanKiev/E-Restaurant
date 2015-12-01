package com.bionic.edu.bean;

import com.bionic.edu.entity.OrderDishes;
import com.bionic.edu.service.OrderDishesService;
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
        orderDishesList.forEach(System.out::println);
    }

    // todo - dishes don't set as ready
    public String markDone(String orderDishId) {
        orderDishesService.setDishReady(Integer.valueOf(orderDishId));
        return "kitchen";
    }
}
