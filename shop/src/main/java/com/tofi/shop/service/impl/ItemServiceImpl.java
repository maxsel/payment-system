package com.tofi.shop.service.impl;


import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.ItemDAO;
import com.tofi.shop.domain.Item;
import com.tofi.shop.service.ItemService;
import com.tofi.shop.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.List;

/**
 * Service with methods
 * encapsulating business logic concerned with {@link Item}s
 * and transaction management.
 */
@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger LOG = LogManager.getLogger(ItemServiceImpl.class);

    private final ItemDAO itemDAO;

    @Inject
    public ItemServiceImpl(ItemDAO itemDAO) {
        Assert.notNull(itemDAO, "ItemDAO must be not null!");
        this.itemDAO = itemDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer create(Item item) throws ServiceException {
        try {
            return itemDAO.insert(item);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Item item) throws ServiceException {
        try {
            itemDAO.update(item);
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
            itemDAO.delete(id);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> findAll() throws ServiceException {
        try {
            return itemDAO.findAll();
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item findById(Integer id) throws ServiceException {
        try {
            return itemDAO.findById(id);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }
}
