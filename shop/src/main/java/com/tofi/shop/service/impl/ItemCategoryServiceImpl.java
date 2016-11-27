package com.tofi.shop.service.impl;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.ItemCategoryDAO;
import com.tofi.shop.domain.ItemCategory;
import com.tofi.shop.service.ItemCategoryService;
import com.tofi.shop.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {
    private final ItemCategoryDAO itemCategoryDAO;
    private static final Logger LOG = LogManager.getLogger(ItemCategoryServiceImpl.class);

    @Inject
    public ItemCategoryServiceImpl(ItemCategoryDAO itemCategoryDAO) {
        this.itemCategoryDAO = itemCategoryDAO;
    }

    @Override
    public Integer create(ItemCategory itemCategory) throws ServiceException {
        try {
            return itemCategoryDAO.insert(itemCategory);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(ItemCategory itemCategory) throws ServiceException {
        try {
            itemCategoryDAO.update(itemCategory);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            itemCategoryDAO.delete(id);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ItemCategory> findAll() throws ServiceException {
        try {
            return itemCategoryDAO.findAll();
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public ItemCategory findById(Integer id) throws ServiceException {
        try {
            return itemCategoryDAO.findById(id);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }
}
