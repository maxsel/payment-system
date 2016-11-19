package com.tofi.shop.service.impl;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.ItemInOrderDAO;
import com.tofi.shop.domain.ItemInOrder;
import com.tofi.shop.service.ItemInOrderService;
import com.tofi.shop.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ItemInOrderServiceImpl implements ItemInOrderService {
    private final ItemInOrderDAO itemInOrderDAO;
    private static final Logger LOG = LogManager.getLogger(ItemInOrderServiceImpl.class);

    @Inject
    public ItemInOrderServiceImpl(ItemInOrderDAO itemInOrderDAO) {
        this.itemInOrderDAO = itemInOrderDAO;
    }

    @Override
    public Integer create(ItemInOrder itemInOrder) throws ServiceException {
        try {
            return itemInOrderDAO.insert(itemInOrder);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(ItemInOrder itemInOrder) throws ServiceException {
        try {
            itemInOrderDAO.update(itemInOrder);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            itemInOrderDAO.delete(id);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ItemInOrder> findAll() throws ServiceException {
        try {
            return itemInOrderDAO.findAll();
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public ItemInOrder findById(Integer id) throws ServiceException {
        try {
            return itemInOrderDAO.findById(id);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }
}
