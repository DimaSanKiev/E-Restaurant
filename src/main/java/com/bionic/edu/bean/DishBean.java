package com.bionic.edu.bean;

import com.bionic.edu.entity.Dish;
import com.bionic.edu.entity.DishCategory;
import com.bionic.edu.service.DishCategoryService;
import com.bionic.edu.service.DishService;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.*;

@Named
@Scope("session")
public class DishBean {
    @Inject
    private DishService dishService;
    @Inject
    private DishCategoryService dishCategoryService;
    private List<Dish> dishes;
    private Dish dish;
    private Map<String, String> idNameCategoryMap;
    private Map<String, DishCategory> idCategoryMap;
    private String category;
    private Part file;
    private String fileContent;
    private UploadedFile uploadedFile;

    public DishService getDishService() {
        return dishService;
    }

    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    public DishCategoryService getDishCategoryService() {
        return dishCategoryService;
    }

    public void setDishCategoryService(DishCategoryService dishCategoryService) {
        this.dishCategoryService = dishCategoryService;
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

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
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

    public void refreshDishes() {
        dishes = dishService.findAll();
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

    public void uploadPhoto() {
        try {
//            fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
            file.write("resources/images/" + getFilename(file));
        } catch (IOException e) {
            // todo - handle
        }
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    public void validateFile(FacesContext context, UIComponent component, Object value) {
        List<FacesMessage> messages = new ArrayList<FacesMessage>();
        Part file = (Part) value;
        if (file.getSize() > 1024000) {
            messages.add(new FacesMessage("File is too big."));
        }
//        if (!"image".equals(file.getContentType())) {
//            messages.add(new FacesMessage("Not an image file."));
//        }
        try {
            ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            messages.add(new FacesMessage("Not an image file."));
        }
        if (!messages.isEmpty()) {
            throw new ValidatorException(messages);
        }
    }
}
