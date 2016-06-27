package com.bionic.edu.service;

import com.bionic.edu.entity.Photo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class PhotoServiceImplTest {

    private PhotoService photoService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");
        photoService = context.getBean(PhotoService.class);
    }

    @Test
    public void findAllReturnsList() throws Exception {
        List<Photo> photos = photoService.findAll();
        photos.forEach(System.out::println);
        assertEquals(18, photos.size());
    }

}