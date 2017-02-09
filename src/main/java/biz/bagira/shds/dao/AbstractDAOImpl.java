package biz.bagira.shds.dao;

import biz.bagira.shds.entity.AbstractEntity;
import biz.bagira.shds.service.DBService;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy on 06.10.2016.
 */
public abstract class AbstractDAOImpl<T extends AbstractEntity> implements AbstractDAO<T> {

    final static Logger logger = Logger.getLogger(AbstractDAOImpl.class);

    DBService dbService = new DBService();

    public  T create(T entity) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            connection = dbService.getConnection();
            String query = getCreateQuery();
            pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            fillCreateStatement(pstmt, entity);
            pstmt.executeUpdate();

            resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()) {
                Integer generatedId = resultSet.getInt(1);
                logger.debug("Method: create "+getById(generatedId));
                return getById(generatedId);
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        } finally {
            if (resultSet != null) try {
                resultSet.close();
            } catch (SQLException logOrIgnore) {
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException logOrIgnore) {
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException logOrIgnore) {
            }
        }

        return null;
    }

    public void delete(T entity) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = dbService.getConnection();
            String query = getDeleteQuery();
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, entity.getId());
            pstmt.execute();
            logger.debug("Method: delete");
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        } finally {
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException logOrIgnore) {
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException logOrIgnore) {
            }
        }
    }

    public T edit(T entity) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = dbService.getConnection();
            String query = getEditQuery();
            pstmt = connection.prepareStatement(query);
            fillEditStatement(pstmt, entity);
            pstmt.executeUpdate();
            logger.debug("Method: edit "+entity);
            return entity;
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        } finally {
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException logOrIgnore) {
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException logOrIgnore) {
            }
        }

        return null;
    }

    public T getById(Integer id) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            connection = dbService.getConnection();
            String query = getGetByIdQuery();
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                logger.debug("Method: getById "+getEntity(resultSet));
                return getEntity(resultSet);
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        } finally {
            if (resultSet != null) try {
                resultSet.close();
            } catch (SQLException logOrIgnore) {
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException logOrIgnore) {
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException logOrIgnore) {
            }
        }
        return null;
    }

    public List<T> getAll() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        List<T> list = new ArrayList<T>();
        try {
            connection = dbService.getConnection();
            String query = getGetAllQuery();
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                list.add(getEntity(resultSet));
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        } finally {
            if (resultSet != null) try {
                resultSet.close();
            } catch (SQLException logOrIgnore) {
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException logOrIgnore) {
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException logOrIgnore) {
            }
        }
        logger.debug("Method: getAll "+list);
        return list;
    }


    public abstract void fillCreateStatement(PreparedStatement pstmt, T entity);

    public abstract void fillEditStatement(PreparedStatement pstmt, T entity);

    public abstract T getEntity(ResultSet resultSet);


    public abstract String getCreateQuery();

    public abstract String getDeleteQuery();

    public abstract String getEditQuery();

    public abstract String getGetByIdQuery();

    public abstract String getGetAllQuery();
}
