package com.tofi.shop.service.impl;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.OrderHistoryDAO;
import com.tofi.shop.domain.OrderHistory;
import com.tofi.shop.service.OrderHistoryService;
import com.tofi.shop.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {
    private final OrderHistoryDAO orderHistoryDAO;
    private static final Logger LOG = LogManager.getLogger(OrderHistoryServiceImpl.class);

    @Inject
    public OrderHistoryServiceImpl(OrderHistoryDAO orderHistoryDAO) {
        this.orderHistoryDAO = orderHistoryDAO;
    }

    @Override
    public Integer create(OrderHistory orderHistory) throws ServiceException {
        try {
            return orderHistoryDAO.insert(orderHistory);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(OrderHistory orderHistory) throws ServiceException {
        try {
            orderHistoryDAO.update(orderHistory);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            orderHistoryDAO.delete(id);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OrderHistory> findAll() throws ServiceException {
        try {
            return orderHistoryDAO.findAll();
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public OrderHistory findById(Integer id) throws ServiceException {
        try {
            return orderHistoryDAO.findById(id);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }
}
