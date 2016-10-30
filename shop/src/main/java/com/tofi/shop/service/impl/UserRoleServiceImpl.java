package com.tofi.shop.service.impl;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.UserRoleDAO;
import com.tofi.shop.domain.UserRole;
import com.tofi.shop.service.ServiceException;
import com.tofi.shop.service.UserRoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger logger = LogManager.getLogger(UserRoleServiceImpl.class);

    private final UserRoleDAO userRoleDAO;

    @Inject
    public UserRoleServiceImpl(UserRoleDAO userRoleDAO) {
        Assert.notNull(userRoleDAO, "UserRoleDAO must be not null!");
        this.userRoleDAO = userRoleDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer create(UserRole role) throws ServiceException {
        try {
            return userRoleDAO.insert(role);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(UserRole role) throws ServiceException {
        try {
            userRoleDAO.update(role);
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
            userRoleDAO.delete(id);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserRole> findAll() throws ServiceException {
        try {
            return userRoleDAO.findAll();
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRole findById(Integer id) throws ServiceException {
        try {
            return userRoleDAO.findById(id);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
