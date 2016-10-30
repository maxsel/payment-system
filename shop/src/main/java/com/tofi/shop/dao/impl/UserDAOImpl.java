package com.tofi.shop.dao.impl;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.UserDAO;
import com.tofi.shop.domain.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    @Inject
    public UserDAOImpl(SessionFactory sessionFactory) {
        Assert.notNull(sessionFactory, "SessionFactory must be not null!");
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Integer insert(User user) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(user);
    }

    @Override
    @Transactional
    public void update(User user) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        session.delete(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
//        criteria.addOrder(Order.asc("name").ignoreCase());
        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Integer id) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.get(User.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByLogin(String login) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class)
                .add(Restrictions.eq("login", login));
        List<User> list = criteria.list();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
