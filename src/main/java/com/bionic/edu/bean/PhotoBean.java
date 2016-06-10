package com.bionic.edu.bean;

import com.bionic.edu.entity.Dish;
import com.bionic.edu.service.DishService;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Named
@Scope("session")
public class PhotoBean {

    @Inject
    private DishService dishService;

//    public void saveDishPhoto(String photoFilePath) throws IOException {
//        Person person = new Person("Tom");
//        byte[] photoBytes = readBytesFromFile(photoFilePath);
//        person.setPhoto(photoBytes);
//        session.save(person);
//    }

    public void readDishPhoto(int dishId) throws IOException {
        Dish dish = dishService.findById(dishId);
        byte[] photoBytes = dish.getPhoto();
        saveBytesToFile("resources/cached_images/", photoBytes);
    }

    private byte[] readBytesFromFile(String filePath) throws IOException {
        File inputFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] fileBytes = new byte[(int) inputFile.length()];
        inputStream.read(fileBytes);
        inputStream.close();
        return fileBytes;
    }

    private void saveBytesToFile(String filePath, byte[] fileBytes) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(fileBytes);
        outputStream.close();
    }
}
