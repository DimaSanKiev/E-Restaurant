package com.bionic.edu.service;

import com.bionic.edu.entity.Photo;

import java.util.List;

public interface PhotoService {

    Photo findById(int id);

    List<Photo> findAll();

    void save(Photo role);

    void delete(int id);
}
