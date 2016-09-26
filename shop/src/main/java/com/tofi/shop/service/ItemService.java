package com.tofi.shop.service;

import com.tofi.shop.domain.Item;

import java.util.List;

/**
 * Service interface with methods
 * encapsulating business logic concerned with {@link Item}s
 * and transaction management.
 */
@SuppressWarnings("unused")
public interface ItemService {

    /**
     * Creates new item.
     *
     * @param item item to create.
     * @return id of created item.
     * @throws ServiceException if an error occurred when performing operation
     */
    Long create(Item item) throws ServiceException;

    /**
     * Edits existing item by id.
     *
     * @param item item to update.
     * @throws ServiceException if an error occurred when performing operation
     */
    void update(Item item) throws ServiceException;

    /**
     * Deletes item by id.
     *
     * @param id id of item to delete.
     * @throws ServiceException if an error occurred when performing operation
     */
    void delete(Long id) throws ServiceException;

    /**
     * Retrieves list of all existing items.
     *
     * @return {@link List} of items.
     * @throws ServiceException if an error occurred when performing operation
     */
    List<Item> findAll() throws ServiceException;

    /**
     * Retrieves an item with the given id.
     *
     * @param id id of item to retrieve.
     * @return an item with the given id.
     * @throws ServiceException if an error occurred when performing operation
     */
    Item findById(Long id) throws ServiceException;
}