package com.bionic.edu.bean;

import com.bionic.edu.entity.Photo;
import com.bionic.edu.service.DishService;
import com.bionic.edu.service.PhotoService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;

@Named
@Scope("session")
public class PhotoBean {

    @Inject
    private PhotoService photoService;

    public StreamedContent getPhotoContent() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String photoId = context.getExternalContext().getRequestParameterMap().get("id");
            Photo photo = photoService.findById(Integer.valueOf(photoId));
            return new DefaultStreamedContent(new ByteArrayInputStream(photo.getContent()));
        }
    }

//    public void saveDishPhoto(String photoFilePath) throws IOException {
//        Person person = new Person("Tom");
//        byte[] photoBytes = readBytesFromFile(photoFilePath);
//        person.setPhoto(photoBytes);
//        session.save(person);
//    }

//    public void readDishPhoto(int dishId) throws IOException {
//        Dish dish = dishService.findById(dishId);
//        byte[] photoBytes = dish.getPhoto();
//        saveBytesToFile("resources/cached_images/", photoBytes);
//    }

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
