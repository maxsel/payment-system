package com.tofi.shop.dao.impl;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.ItemInOrderDAO;
import com.tofi.shop.domain.ItemInOrder;
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
public class ItemInOrderDAOImpl implements ItemInOrderDAO {

    private SessionFactory sessionFactory;

    @Inject
    public ItemInOrderDAOImpl(SessionFactory sessionFactory) {
        Assert.notNull(sessionFactory, "SessionFactory must be not null!");
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Integer insert(ItemInOrder item) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(item);
    }

    @Override
    @Transactional
    public void update(ItemInOrder item) throws DAOException {
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
    public List<ItemInOrder> findAll() throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(ItemInOrder.class);
//        criteria.addOrder(Order.asc("name").ignoreCase());
        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public ItemInOrder findById(Integer id) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        return (ItemInOrder) session.get(ItemInOrder.class, id);
    }
}
