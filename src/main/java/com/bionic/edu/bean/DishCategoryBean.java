package com.bionic.edu.bean;

import com.bionic.edu.entity.DishCategory;
import com.bionic.edu.service.DishCategoryService;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Scope("session")
public class DishCategoryBean implements Serializable {
    private static final long serialVersionUID = 2836340733987093740L;

    @Inject
    private DishCategoryService dishCategoryService;
    private List<DishCategory> dishCategories = null;

    public List<DishCategory> getDishCategories() {
        return dishCategories;
    }

    public void setDishCategories(List<DishCategory> dishCategories) {
        this.dishCategories = dishCategories;
    }

    public void refreshList() {
        dishCategories = dishCategoryService.findAll();
    }
}
