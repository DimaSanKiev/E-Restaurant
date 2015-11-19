package com.bionic.edu.bean;

import com.bionic.edu.entity.Dish;
import com.bionic.edu.service.DishService;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Scope("session")
public class MenuBean implements Serializable {
    private static final long serialVersionUID = 8582710702656601048L;

    @Inject
    private DishService dishService;
    private List<Dish> dishes;

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void refreshDishes() {
        dishes = dishService.findAll();
    }
}
