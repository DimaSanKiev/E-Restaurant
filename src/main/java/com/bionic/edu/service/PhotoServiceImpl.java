package com.bionic.edu.service;

import com.bionic.edu.dao.PhotoDao;
import com.bionic.edu.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao photoDao;

    @Override
    public Photo findById(int id) {
        return photoDao.findById(id);
    }

    @Override
    public List<Photo> findAll() {
        return photoDao.findAll();
    }

    @Transactional
    @Override
    public void save(Photo photo) {
        photoDao.save(photo);
    }

    @Transactional
    @Override
    public void delete(int id) {
        photoDao.delete(id);
    }
}
