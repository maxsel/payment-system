package com.tofi.shop.dao.impl;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.OrderStatusDAO;
import com.tofi.shop.domain.OrderStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Repository
public class OrderStatusDAOImpl implements OrderStatusDAO {

    private SessionFactory sessionFactory;

    @Inject
    public OrderStatusDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Integer insert(OrderStatus orderStatus) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(orderStatus);
    }

    @Override
    @Transactional
    public void update(OrderStatus orderStatus) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        session.update(orderStatus);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        session.delete(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderStatus> findAll() throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(OrderStatus.class);
//        criteria.addOrder(Order.asc("name").ignoreCase());
        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderStatus findById(Integer id) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        return (OrderStatus) session.get(OrderStatus.class, id);
    }
}
