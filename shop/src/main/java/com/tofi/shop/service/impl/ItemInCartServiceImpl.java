package com.tofi.shop.service.impl;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.ItemInCartDAO;
import com.tofi.shop.domain.ItemInCart;
import com.tofi.shop.service.ItemInCartService;
import com.tofi.shop.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.List;

@Service
public class ItemInCartServiceImpl implements ItemInCartService {

    private static final Logger LOG = LogManager.getLogger(ItemInCartServiceImpl.class);

    private final ItemInCartDAO itemInCartDAO;

    @Inject
    public ItemInCartServiceImpl(ItemInCartDAO itemInCartDAO) {
        Assert.notNull(itemInCartDAO, "ItemInCartDAO must be not null!");
        this.itemInCartDAO = itemInCartDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer create(ItemInCart item) throws ServiceException {
        try {
            return itemInCartDAO.insert(item);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ItemInCart item) throws ServiceException {
        try {
            itemInCartDAO.update(item);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("Duplicates")
    @Transactional(rollbackFor = ServiceException.class)
    public void delete(Integer id) throws ServiceException {
        try {
            itemInCartDAO.delete(id);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ItemInCart> findAll() throws ServiceException {
        try {
            return itemInCartDAO.findAll();
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemInCart findById(Integer id) throws ServiceException {
        try {
            return itemInCartDAO.findById(id);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }
}
