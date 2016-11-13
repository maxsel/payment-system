package com.tofi.shop.service;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.OrderStatusDAO;
import com.tofi.shop.domain.OrderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class OrderStatusServiceImpl implements OrderStatusService {
    private final OrderStatusDAO orderStatusDAO;
    private static final Logger LOG = LogManager.getLogger(OrderStatusServiceImpl.class);

    @Inject
    public OrderStatusServiceImpl(OrderStatusDAO orderStatusDAO) {
        this.orderStatusDAO = orderStatusDAO;
    }

    @Override
    public Integer create(OrderStatus orderStatus) throws ServiceException {
        try {
            return orderStatusDAO.insert(orderStatus);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(OrderStatus orderStatus) throws ServiceException {
        try {
            orderStatusDAO.update(orderStatus);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            orderStatusDAO.delete(id);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OrderStatus> findAll() throws ServiceException {
        try {
            return orderStatusDAO.findAll();
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public OrderStatus findById(Integer id) throws ServiceException {
        try {
            return orderStatusDAO.findById(id);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }
}
