package com.bionic.edu.dao;

import com.bionic.edu.dao.generic.GenericDao;
import com.bionic.edu.entity.Photo;
import org.springframework.stereotype.Repository;

@Repository
public class PhotoDaoImpl extends GenericDao<Photo> implements PhotoDao {
}
