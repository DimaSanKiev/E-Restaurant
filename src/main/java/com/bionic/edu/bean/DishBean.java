package com.bionic.edu.bean;

import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.DishCategory;
import com.bionic.edu.service.DishCategoryService;
import com.bionic.edu.service.DishService;
import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@RequestScoped
@Scope("session")
public class DishBean implements Serializable {
    private static final long serialVersionUID = 1308298924092691297L;

    @Inject
    private DishService dishService;
    @Inject
    private DishCategoryService dishCategoryService;
    private List<Dish> dishes;
    private Dish dish;
    private Map<String, String> idNameCategoryMap;
    private Map<String, DishCategory> idCategoryMap;
    private String category;
    private UploadedFile uploadedFile; // delete or to do in last step

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

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }


    public void refreshCategories() {
        idNameCategoryMap = new HashMap<>();
        idCategoryMap = new HashMap<>();
        List<DishCategory> dishCategories = dishCategoryService.findAll();
        for (DishCategory dc : dishCategories) {
            idNameCategoryMap.put(dc.getName(), String.valueOf(dc.getId()));
            idCategoryMap.put(String.valueOf(dc.getId()), dc);
        }
    }

    public void refreshAllDishes() {
        dishes = dishService.findAll();
    }

    public void refreshAvailableDishes() {
        dishes = dishService.findByAvailability(true);
    }

    public String saveDish() {
        dish.setCategory(idCategoryMap.get(category));
        dishService.save(dish);
        return "dishList";
    }

    public String addDish() {
        refreshCategories();
        dish = new Dish();
        return "newDish";
    }

    public String updateDish(String id) {
        refreshCategories();
        int n = Integer.valueOf(id);
        dish = dishService.findById(n);
        return "newDish";
    }

    /*public String showCategory(String id) {
        int n = Integer.valueOf(id);
        dishes = dishService.findByCategory(n);
        return "dishByCategoryList";
    }*/


    /*public void submit() throws IOException {
        String fileName = FilenameUtils.getName(uploadedFile.getName());
        String contentType = uploadedFile.getContentType();
        byte[] bytes = uploadedFile.getBytes();
        FileOutputStream fos = new FileOutputStream("resources/images/" + fileName + ".jpg");
        fos.write(bytes);
        fos.close();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(String.format("File '%s' of type '%s' successfully uploaded!", fileName, contentType)));
    }*/

}
