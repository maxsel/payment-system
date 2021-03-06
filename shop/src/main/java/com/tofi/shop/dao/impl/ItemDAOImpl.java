package com.tofi.shop.dao.impl;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.ItemDAO;
import com.tofi.shop.domain.Item;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.List;

/**
 * Data Access Object with methods
 * for accessing {@link Item}s in the database.
 */
@Repository
@SuppressWarnings({"Duplicates", "unchecked"})
public class ItemDAOImpl implements ItemDAO {

    private SessionFactory sessionFactory;

    @Inject
    public ItemDAOImpl(SessionFactory sessionFactory) {
        Assert.notNull(sessionFactory, "SessionFactory must be not null!");
        this.sessionFactory = sessionFactory;
    }

    /**
     * Persists item in the database.
     *
     * @param item item to persist.
     * @return id of inserted item.
     * @throws DAOException if database error occurred
     */
    @Override
    @Transactional
    public Integer insert(Item item) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(item);
    }

    /**
     * Updates all fields of item in database by id.
     *
     * @param item item to update.
     * @throws DAOException if database error occurred
     */
    @Override
    @Transactional
    public void update(Item item) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        session.update(item);
    }

    /**
     * Deletes item by id.
     *
     * @param id id of item to delete.
     * @throws DAOException if database error occurred
     */
    @Override
    @Transactional
    public void delete(Integer id) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        session.delete(findById(id));
    }

    /**
     * Retrieves all items from the needed table of the database.
     *
     * @return {@link List} of items.
     * @throws DAOException if database error occurred
     */
    @Override
    @Transactional(readOnly = true)
    public List<Item> findAll() throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Item.class);
//        criteria.addOrder(Order.asc("name").ignoreCase());
        return criteria.list();
    }

    /**
     * Retrieves item with the given id.
     *
     * @param id id of item to retrieve.
     * @return item with the given id.
     * @throws DAOException if database error occurred
     */
    @Override
    @Transactional(readOnly = true)
    public Item findById(Integer id) throws DAOException {
        Session session = sessionFactory.getCurrentSession();
        return (Item) session.get(Item.class, id);
    }
}
