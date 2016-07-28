package com.bionic.edu.dao.generic;

import java.util.List;

/**
 * Interface for Data Access Object (DAO). It acts as a manager
 * responsible for the persistence operations on a specific type {@code T}.
 * It is responsible for loading, saving, searching and deleting entities of
 * the according type.
 *
 * @param <T> Type of an entity
 */
public interface GenericDao<T> {

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id of an entity
     * @return the entity with the given id or {@literal null} if none found
     */
    T findById(int id);

    /**
     * Returns all instances of a type.
     *
     * @return all entities
     */
    List<T> findAll();

    /**
     * Saves a given entity.
     *
     * @param t Entity type
     */
    void save(T t);

    /**
     * Deletes the entity with the given id.
     *
     * @param id of an entity
     */
    void delete(int id);
}
