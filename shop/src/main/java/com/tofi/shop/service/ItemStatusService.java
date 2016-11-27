package com.tofi.shop.service;

import com.tofi.shop.domain.ItemStatus;

import java.util.List;

public interface ItemStatusService {

    /**
     * Creates new item.
     *
     * @param itemStatus item to create.
     * @return id of created item.
     * @throws ServiceException if an error occurred when performing operation
     */
    Integer create(ItemStatus itemStatus) throws ServiceException;

    /**
     * Edits existing item by id.
     *
     * @param itemStatus item to update.
     * @throws ServiceException if an error occurred when performing operation
     */
    void update(ItemStatus itemStatus) throws ServiceException;

    /**
     * Deletes item by id.
     *
     * @param id id of item to delete.
     * @throws ServiceException if an error occurred when performing operation
     */
    void delete(Integer id) throws ServiceException;

    /**
     * Retrieves list of all existing items.
     *
     * @return {@link List} of items.
     * @throws ServiceException if an error occurred when performing operation
     */
    List<ItemStatus> findAll() throws ServiceException;

    /**
     * Retrieves an item with the given id.
     *
     * @param id id of item to retrieve.
     * @return an item with the given id.
     * @throws ServiceException if an error occurred when performing operation
     */
    ItemStatus findById(Integer id) throws ServiceException;
}
