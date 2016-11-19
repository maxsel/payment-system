package com.tofi.shop.service.impl;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.OrderDAO;
import com.tofi.shop.domain.Order;
import com.tofi.shop.service.OrderService;
import com.tofi.shop.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;
    private static final Logger LOG = LogManager.getLogger(OrderServiceImpl.class);

    @Inject
    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public Integer create(Order order) throws ServiceException {
        try {
            return orderDAO.insert(order);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Order order) throws ServiceException {
        try {
            orderDAO.update(order);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            orderDAO.delete(id);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        try {
            return orderDAO.findAll();
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Order findById(Integer id) throws ServiceException {
        try {
            return orderDAO.findById(id);
        } catch (DAOException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }
}
