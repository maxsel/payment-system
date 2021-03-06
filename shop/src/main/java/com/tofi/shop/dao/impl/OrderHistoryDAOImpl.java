package com.tofi.shop.dao.impl;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.OrderHistoryDAO;
import com.tofi.shop.domain.OrderHistory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.List;

@Repository
public class OrderHistoryDAOImpl implements OrderHistoryDAO {
    private SessionFactory sessionFactory;

    @Inject
    public OrderHistoryDAOImpl(SessionFactory sessionFactory) {
        Assert.notNull(sessionFactory, "SessionFactory must be not null!");
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Integer insert(OrderHistory item) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(item);
    }

    @Override
    @Transactional
    public void update(OrderHistory item) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        session.update(item);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        session.delete(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderHistory> findAll() throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(OrderHistory.class);
//        criteria.addOrder(Order.asc("name").ignoreCase());
        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderHistory findById(Integer id) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        return (OrderHistory) session.get(OrderHistory.class, id);
    }
}
