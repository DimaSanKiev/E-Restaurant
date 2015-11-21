package com.bionic.edu.bean;

import com.bionic.edu.entity.Dish;
import com.bionic.edu.service.DishService;
import com.bionic.edu.service.OrderService;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@Scope("session")
public class CartBean implements Serializable {
    private static final long serialVersionUID = -2351220622598691145L;
    private Map<Dish, Integer> dishCountMap;
    private Dish dish;
    private int count = 1;
    private double total;
    @Inject
    private CustomerBean customerBean;
    @Inject
    private DishService dishService;
    @Inject
    private OrderService orderService;

    public CartBean() {
        dishCountMap = new HashMap<>();
        total = 0.0;
    }

    public Map<Dish, Integer> getDishCountMap() {
        return dishCountMap;
    }

    public void setDishCountMap(Map<Dish, Integer> dishCountMap) {
        this.dishCountMap = dishCountMap;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    public void addToCart(int id, String count) {
        int intCount = Integer.valueOf(count);
        Dish dish = dishService.findById(id);
        if (!dishCountMap.containsKey(dish)) {
            dishCountMap.put(dish, 1);
        } else {
            dishCountMap.put(dish, dishCountMap.get(dish) + 1);
        }
    }

    public void updateTotal() {
        total = 0.0;
        for (Map.Entry<Dish, Integer> entry : dishCountMap.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
    }

    public void changeCount() {
        dishCountMap.put(dish, count);
        updateTotal();
    }

    public void update(int id, String count) {
        Dish dish = dishService.findById(id);
        dishCountMap.put(dish, Integer.valueOf(count));
    }

    public void remove(int id) {
        Dish dish = dishService.findById(id);
        dishCountMap.remove(dish);
        updateTotal();
    }

}
