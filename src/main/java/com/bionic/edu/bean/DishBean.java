package com.bionic.edu.bean;

import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.DishCategory;
import com.bionic.edu.service.DishCategoryService;
import com.bionic.edu.service.DishService;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bionic.edu.util.GlowlMessenger.addMessage;

@Named
@Scope("session")
public class DishBean implements Serializable {
    private static final long serialVersionUID = 1308298924092691297L;

    @Inject
    private DishService dishService;
    @Inject
    private DishCategoryService dishCategoryService;
    @Inject
    private PhotoBean photoBean;
    private List<Dish> dishes;
    private Dish dish;
    private Map<String, String> idNameCategoryMap;
    private Map<String, DishCategory> idCategoryMap;
    private String category;
    private DishCategory dishCategory;

    public DishBean() {
        dish = new Dish();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Map<String, String> getIdNameCategoryMap() {
        return idNameCategoryMap;
    }

    public void setIdNameCategoryMap(Map<String, String> idNameCategoryMap) {
        this.idNameCategoryMap = idNameCategoryMap;
    }

    public Map<String, DishCategory> getIdCategoryMap() {
        return idCategoryMap;
    }

    public void setIdCategoryMap(Map<String, DishCategory> idCategoryMap) {
        this.idCategoryMap = idCategoryMap;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public DishCategory getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
    }

    public void refreshCategories() {
        idNameCategoryMap = new HashMap<>();
        idCategoryMap = new HashMap<>();
        List<DishCategory> dishCategories = dishCategoryService.findAll();
        for (DishCategory dishCategory : dishCategories) {
            idNameCategoryMap.put(dishCategory.getName(), String.valueOf(dishCategory.getId()));
            idCategoryMap.put(String.valueOf(dishCategory.getId()), dishCategory);
        }
    }

    public void refreshAllDishes() {
        dishes = dishService.findAll();
    }

    public void refreshAvailableDishes() {
        dishes = dishService.findByAvailability(true);
        dishCategory = null;
    }

    public void refreshCategory(int categoryId) {
        dishes = dishService.findByCategory(categoryId);
        dishCategory = dishCategoryService.findById(categoryId);
    }

    public String saveDish() {
        dish.setCategory(idCategoryMap.get(category));
        dish.setPhoto(photoBean.getPhoto());
        if (dish.getCategory() == null) {
            addMessage("Category is required", "Please select dish category.", FacesMessage.SEVERITY_ERROR);
            return null;
        }
        dishService.save(dish);
        addMessage("Success", "Dish saved successfully!", FacesMessage.SEVERITY_INFO);
        return "dishList";
    }

    public void changeAvailability(int dishId) {
        dishService.changeAvailability(dishId);
    }

    public String addDish() {
        refreshCategories();
        photoBean.setPhoto(null);
        dish = new Dish();
        return "newDish";
    }

    public String updateDish(String id) {
        refreshCategories();
        dish = dishService.findById(Integer.valueOf(id));
        if (dish != null) {
            photoBean.setPhoto(dish.getPhoto());
        }
        return "newDish";
    }

    public String refreshCategory(String id) {
        int n = Integer.valueOf(id);
        dishes = dishService.findByCategory(n);
        return "dishByCategoryList";
    }

}
