package biz.bagira.shds.dao;

import biz.bagira.shds.entity.Category;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by Dmitriy on 06.10.2016.
 */
public class CategoryDAO extends AbstractDAOImpl<Category> {

    final static Logger logger = Logger.getLogger(CategoryDAO.class);


    public Integer getIdCategoryByType(String type) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            connection = dbService.getConnection();
            String query = dbService.getQuery("get.idcategory.by.type");
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, type);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                 logger.debug("Method: getIdCategoryByType = "+ resultSet.getInt(1));
                return resultSet.getInt(1);
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


        return -1;


    }


    public void fillCreateStatement(PreparedStatement pstmt, Category entity) {
        try {
            pstmt.setString(1, entity.getType());
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    public void fillEditStatement(PreparedStatement pstmt, Category entity) {
        try {
            pstmt.setString(1, entity.getType());
            pstmt.setInt(2, entity.getId());
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    public Category getEntity(ResultSet resultSet) {
        Category category = new Category();
        try {
            category.setId(resultSet.getInt("idcategory"));
            category.setType(resultSet.getString("type"));
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        return category;
    }

    public String getCreateQuery() {

        return dbService.getQuery("create.category");
    }

    public String getDeleteQuery() {
        return dbService.getQuery("delete.category");
    }

    public String getEditQuery() {
        return dbService.getQuery("update.category");
    }

    public String getGetByIdQuery() {
        return dbService.getQuery("get.category.by.id");
    }

    public String getGetAllQuery() {
        return dbService.getQuery("get.all.categories");
    }
}
