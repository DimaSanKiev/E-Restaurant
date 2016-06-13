package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDaoImpl;
import com.bionic.edu.entity.Photo;
import org.springframework.stereotype.Repository;

@Repository
public class PhotoDaoImpl extends GenericDaoImpl<Photo> implements PhotoDao {
}
