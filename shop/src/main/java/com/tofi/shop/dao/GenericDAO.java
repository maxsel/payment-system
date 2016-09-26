package com.tofi.shop.dao;

import java.util.List;

/**
 * Generic DataAccessObject interface with basic CRUD methods
 * for accessing entities in the database.
 *
 * @param <K> key type
 * @param <V> value type
 */
public interface GenericDAO<K, V> {
    /**
     * Persists an item in the database.
     *
     * @param item item to persist.
     * @return id of inserted item.
     * @throws DAOException if database error occurred
     */
    K insert(V item) throws DAOException;

    /**
     * Updates all fields of an item in database by id.
     *
     * @param item item to update.
     * @throws DAOException if database error occurred
     */
    void update(V item) throws DAOException;

    /**
     * Deletes an item by id.
     *
     * @param id id of an item to delete.
     * @throws DAOException if database error occurred
     */
    void delete(K id) throws DAOException;

    /**
     * Retrieves all items from the needed table of the database.
     *
     * @return {@link List} of items.
     * @throws DAOException if database error occurred
     */
    List<V> findAll() throws DAOException;

    /**
     * Retrieves an item with the given id.
     *
     * @param id id of an item to retrieve.
     * @return an item with the given id.
     * @throws DAOException if database error occurred
     */
    V findById(K id) throws DAOException;
}