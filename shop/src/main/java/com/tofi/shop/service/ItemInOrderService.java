package com.tofi.shop.service;

import com.tofi.shop.domain.ItemInOrder;

import java.util.List;

public interface ItemInOrderService {

    /**
     * Creates new item.
     *
     * @param itemInOrder item to create.
     * @return id of created item.
     * @throws ServiceException if an error occurred when performing operation
     */
    Integer create(ItemInOrder itemInOrder) throws ServiceException;

    /**
     * Edits existing item by id.
     *
     * @param itemInOrder item to update.
     * @throws ServiceException if an error occurred when performing operation
     */
    void update(ItemInOrder itemInOrder) throws ServiceException;

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
    List<ItemInOrder> findAll() throws ServiceException;

    /**
     * Retrieves an item with the given id.
     *
     * @param id id of item to retrieve.
     * @return an item with the given id.
     * @throws ServiceException if an error occurred when performing operation
     */
    ItemInOrder findById(Integer id) throws ServiceException;
}
