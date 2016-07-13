package com.bionic.edu.bean;

import com.bionic.edu.entity.Photo;
import com.bionic.edu.service.PhotoService;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
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
    private Photo photo;

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public StreamedContent getPhotoContent() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String photoId = context.getExternalContext().getRequestParameterMap().get("id");
            Photo photo = photoService.findById(Integer.valueOf(photoId));
            this.photo = photo;
            return new DefaultStreamedContent(new ByteArrayInputStream(photo.getContent()));
        }
    }

    public int handleFileUpload(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        photo = new Photo(IOUtils.toByteArray(file.getInputstream()));
        photoService.save(photo);
        return photo.getId();
    }

}