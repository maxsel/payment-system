package com.tofi.shop.service;

import com.tofi.shop.domain.User;

import java.util.List;

/**
 * Service interface with methods
 * encapsulating business logic concerned with {@link User}s
 * and transaction management.
 */
@SuppressWarnings("unused")
public interface UserService {

    /**
     * Creates new user.
     *
     * @param user user to create.
     * @return id of created user.
     * @throws ServiceException if an error occurred when performing operation
     */
    Integer create(User user) throws ServiceException;

    /**
     * Edits existing user by id.
     *
     * @param user user to update.
     * @throws ServiceException if an error occurred when performing operation
     */
    void update(User user) throws ServiceException;

    /**
     * Deletes user by id.
     *
     * @param id id of user to delete.
     * @throws ServiceException if an error occurred when performing operation
     */
    void delete(Integer id) throws ServiceException;

    /**
     * Retrieves list of all existing users.
     *
     * @return {@link List} of users.
     * @throws ServiceException if an error occurred when performing operation
     */
    List<User> findAll() throws ServiceException;

    /**
     * Retrieves an user with the given id.
     *
     * @param id id of user to retrieve.
     * @return an user with the given id.
     * @throws ServiceException if an error occurred when performing operation
     */
    User findById(Integer id) throws ServiceException;

    User findByLogin(String login) throws ServiceException;
}