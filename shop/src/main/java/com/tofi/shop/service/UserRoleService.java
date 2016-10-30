package com.tofi.shop.service;

import com.tofi.shop.domain.UserRole;

import java.util.List;

public interface UserRoleService {

    Integer create(UserRole role) throws ServiceException;

    void update(UserRole role) throws ServiceException;

    void delete(Integer id) throws ServiceException;

    List<UserRole> findAll() throws ServiceException;

    UserRole findById(Integer id) throws ServiceException;
}
