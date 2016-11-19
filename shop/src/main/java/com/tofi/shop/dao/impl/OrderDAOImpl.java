package com.tofi.shop.dao.impl;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.OrderDAO;
import com.tofi.shop.domain.Order;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class OrderDAOImpl implements OrderDAO {

    private SessionFactory sessionFactory;

    @Inject
    public OrderDAOImpl(SessionFactory sessionFactory) {
        Assert.notNull(sessionFactory, "SessionFactory must be not null!");
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Integer insert(Order item) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(item);
    }

    @Override
    @Transactional
    public void update(Order item) throws DAOException {
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
    public List<Order> findAll() throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Order.class);
//        criteria.addOrder(Order.asc("name").ignoreCase());
        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public Order findById(Integer id) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        return (Order) session.get(Order.class, id);
    }
}
