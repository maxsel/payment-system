package com.tofi.shop.service.impl;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.ItemStatusDAO;
import com.tofi.shop.domain.ItemStatus;
import com.tofi.shop.service.ItemStatusService;
import com.tofi.shop.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ItemStatusServiceImpl implements ItemStatusService {
    private final ItemStatusDAO itemStatusDAO;
    private static final Logger LOG = LogManager.getLogger(ItemCategoryServiceImpl.class);

    @Inject
    public ItemStatusServiceImpl(ItemStatusDAO itemStatusDAO) {
        this.itemStatusDAO = itemStatusDAO;
    }

    @Override
    public Integer create(ItemStatus itemStatus) throws ServiceException {
        try {
            return itemStatusDAO.insert(itemStatus);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(ItemStatus itemStatus) throws ServiceException {
        try {
            itemStatusDAO.update(itemStatus);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            itemStatusDAO.delete(id);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ItemStatus> findAll() throws ServiceException {
        try {
            return itemStatusDAO.findAll();
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public ItemStatus findById(Integer id) throws ServiceException {
        try {
            return itemStatusDAO.findById(id);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }
}
