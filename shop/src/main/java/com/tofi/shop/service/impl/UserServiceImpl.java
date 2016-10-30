package com.tofi.shop.service.impl;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.UserDAO;
import com.tofi.shop.domain.User;
import com.tofi.shop.service.ServiceException;
import com.tofi.shop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.List;

/**
 * Service with methods
 * encapsulating business logic concerned with {@link User}s
 * and transaction management.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO;

    @Inject
    public UserServiceImpl(UserDAO userDAO) {
        Assert.notNull(userDAO, "UserDAO must be not null!");
        this.userDAO = userDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer create(User user) throws ServiceException {
        try {
            return userDAO.insert(user);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User user) throws ServiceException {
        try {
            userDAO.update(user);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            userDAO.delete(id);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDAO.findAll();
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findById(Integer id) throws ServiceException {
        try {
            return userDAO.findById(id);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
