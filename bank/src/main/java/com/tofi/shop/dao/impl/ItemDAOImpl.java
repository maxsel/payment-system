package com.tofi.shop.dao.impl;

import com.tofi.shop.dao.DAOException;
import com.tofi.shop.dao.ItemDAO;
import com.tofi.shop.domain.Item;
import com.tofi.shop.domain.ItemCategory;
import com.tofi.shop.domain.ItemStatus;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Data Access Object with methods
 * for accessing {@link Item}s in the database.
 */
@Repository
@SuppressWarnings("Duplicates")
public class ItemDAOImpl implements ItemDAO {

    private static final String SQL_INSERT =
            "INSERT INTO item(item_title, item_desc, item_price, item_img, item_cat_id, item_status_id)" +
                    " VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_BY_ID =
            "UPDATE item" +
                    " SET item_title = ?, item_desc = ?, item_price = ?, item_img = ?, item_cat_id = ?, item_status_id = ?" +
                    " WHERE item_id = ?";
    private static final String SQL_DELETE_BY_ID =
            "DELETE FROM item WHERE item_id = ?";
    private static final String SQL_SELECT_BY_ID =
            "SELECT item_id, item_title, item_desc, item_price, item_img, item_cat_id, item_status_id" +
                    " FROM item WHERE item_id = ?";
    private static final String SQL_SELECT_ALL =
            "SELECT item_id, item_title, item_desc, item_price, item_img, item_cat_id, item_status_id FROM item";
    private static final String COLUMN_ITEM_ID = "item_id";
    private static final String COLUMN_ITEM_TITLE = "item_title";
    private static final String COLUMN_ITEM_DESC = "item_desc";
    private static final String COLUMN_ITEM_PRICE = "item_price";
    private static final String COLUMN_ITEM_IMG = "item_img";
    private static final String COLUMN_ITEM_CAT_ID = "item_item_cat_id";
    private static final String COLUMN_ITEM_STATE_ID = "item_item_status_id";


    private final DataSource dataSource;

    @Inject
    public ItemDAOImpl(DataSource dataSource) {
        Assert.notNull(dataSource, "DataSource must be not null!");
        this.dataSource = dataSource;
    }

    /**
     * Persists item in the database.
     *
     * @param item item to persist.
     * @return id of inserted item.
     * @throws DAOException if database error occurred
     */
    @Override
    public Long insert(Item item) throws DAOException {
        if (item == null) return null;
        Long insertedId = null;
        String[] generated = {COLUMN_ITEM_ID};
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement ps =
                     connection.prepareStatement(SQL_INSERT, generated)) {
            ps.setString(1, item.getTitle());
            ps.setString(2, item.getDescription());
            ps.setDouble(3, item.getPrice());
            ps.setNull(4, Types.BLOB);
            ps.setLong(5, item.getCategory().getId());
            ps.setLong(6, item.getStatus().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                insertedId = generatedKeys.getLong(1);
            } else {
                throw new DAOException("Exception in ItemDAOImpl#insert(Item);" +
                        " arguments: [" + item + "];" +
                        " message: No ID generated");
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in ItemDAOImpl#insert(Item);" +
                    " arguments: [" + item + "];" +
                    " nested exception: [" + e + "]");
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
        return insertedId;
    }

    /**
     * Updates all fields of item in database by id.
     *
     * @param item item to update.
     * @throws DAOException if database error occurred
     */
    @Override
    public void update(Item item) throws DAOException {
        if (item == null) return;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_BY_ID)) {
            ps.setString(1, item.getTitle());
            ps.setString(2, item.getDescription());
            ps.setDouble(3, item.getPrice());
            ps.setNull(4, Types.BLOB);
            ps.setLong(5, item.getCategory().getId());
            ps.setLong(6, item.getStatus().getId());
            ps.setLong(7, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in ItemDAOImpl#update(Item);" +
                    " arguments: [" + item + "];" +
                    " nested exception: [" + e + "]");
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Deletes item by id.
     *
     * @param id id of item to delete.
     * @throws DAOException if database error occurred
     */
    @Override
    public void delete(Long id) throws DAOException {
        if (id == null) return;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement ps =
                     connection.prepareStatement(SQL_DELETE_BY_ID)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in ItemDAOImpl#delete(Long);" +
                    " arguments: [" + id + "];" +
                    " nested exception: [" + e + "]");
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Retrieves all items from the needed table of the database.
     *
     * @return {@link List} of items.
     * @throws DAOException if database error occurred
     */
    @Override
    public List<Item> findAll() throws DAOException {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            return fetchItemsListFromResultSet(rs);
        } catch (SQLException e) {
            throw new DAOException("Exception in ItemDAOImpl#findAll();" +
                    " arguments: [" + "];" +
                    " nested exception: [" + e + "]");
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Retrieves item with the given id.
     *
     * @param id id of item to retrieve.
     * @return item with the given id.
     * @throws DAOException if database error occurred
     */
    @Override
    public Item findById(Long id) throws DAOException {
        if (id == null) return null;
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            return fetchItemFromResultSet(rs);
        } catch (SQLException e) {
            throw new DAOException("Exception in ItemDAOImpl#findById(Long);" +
                    " arguments: [" + id + "];" +
                    " nested exception: [" + e + "]");
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    private Item fetchItemFromResultSet(ResultSet rs)
            throws SQLException {
        Item item = null;
        if (rs.next()) {
            item = new Item();
            item.setId(rs.getLong(COLUMN_ITEM_ID));
            item.setTitle(rs.getString(COLUMN_ITEM_TITLE));
            item.setDescription(rs.getString(COLUMN_ITEM_DESC));
            item.setPrice(rs.getDouble(COLUMN_ITEM_PRICE));
            // TODO:item.setImage
            item.setCategory(new ItemCategory(0L, "Test category"));
            item.setStatus(new ItemStatus(0L, "Test status"));
        }
        return item;
    }

    private List<Item> fetchItemsListFromResultSet(ResultSet rs)
            throws SQLException {
        List<Item> items = new LinkedList<>();
        while (rs.next()) {
            Item item = new Item();
            item.setId(rs.getLong(COLUMN_ITEM_ID));
            item.setTitle(rs.getString(COLUMN_ITEM_TITLE));
            item.setDescription(rs.getString(COLUMN_ITEM_DESC));
            item.setPrice(rs.getDouble(COLUMN_ITEM_PRICE));
            // TODO:item.setImage
            item.setCategory(new ItemCategory(0L, "Test category"));
            item.setStatus(new ItemStatus(0L, "Test status"));
            items.add(item);
        }
        return items;
    }
}
