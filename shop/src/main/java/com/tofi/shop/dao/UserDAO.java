package com.tofi.shop.dao;

import com.tofi.shop.domain.User;

public interface UserDAO extends GenericDAO<Integer, User> {
    User findByLogin(String login) throws DAOException;
}
