package com.tofi.shop.service;

import com.tofi.shop.domain.OrderHistory;

import java.util.List;

public interface OrderHistoryService {

    /**
     * Creates new item.
     *
     * @param orderHistory item to create.
     * @return id of created item.
     * @throws ServiceException if an error occurred when performing operation
     */
    Integer create(OrderHistory orderHistory) throws ServiceException;

    /**
     * Edits existing item by id.
     *
     * @param orderHistory item to update.
     * @throws ServiceException if an error occurred when performing operation
     */
    void update(OrderHistory orderHistory) throws ServiceException;

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
    List<OrderHistory> findAll() throws ServiceException;

    /**
     * Retrieves an item with the given id.
     *
     * @param id id of item to retrieve.
     * @return an item with the given id.
     * @throws ServiceException if an error occurred when performing operation
     */
    OrderHistory findById(Integer id) throws ServiceException;
}
