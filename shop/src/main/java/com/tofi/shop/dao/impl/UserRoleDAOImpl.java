package com.tofi.shop.dao.impl;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.UserRoleDAO;
import com.tofi.shop.domain.UserRole;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.List;

@Repository
public class UserRoleDAOImpl implements UserRoleDAO {
    private SessionFactory sessionFactory;

    @Inject
    public UserRoleDAOImpl(SessionFactory sessionFactory) {
        Assert.notNull(sessionFactory, "SessionFactory must be not null!");
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Integer insert(UserRole role) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(role);
    }

    @Override
    @Transactional
    public void update(UserRole role) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        session.update(role);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        session.delete(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRole> findAll() throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(UserRole.class);
//        criteria.addOrder(Order.asc("name").ignoreCase());
        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public UserRole findById(Integer id) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        return (UserRole) session.get(UserRole.class, id);
    }
}
