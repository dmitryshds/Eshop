package biz.bagira.shds.eshop.dao;

import biz.bagira.shds.eshop.entity.Category;
import biz.bagira.shds.eshop.entity.Product;
import biz.bagira.shds.eshop.entity.Review;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy on 06.10.2016.
 */
public class ProductDAO extends AbstractDAOImpl<Product> {

    final static Logger logger = Logger.getLogger(ProductDAO.class);
    CategoryDAO categoryDAO = new CategoryDAO();
    ReviewDAO reviewDAO = new ReviewDAO();


    public List<Product> getAllbyCategory(Integer idCategory) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        List<Product> list = new ArrayList<Product>();
        try {
            connection = dbService.getConnection();
            String query = dbService.getQuery("get.products.by.category");
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idCategory);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                list.add(getEntity(resultSet));
            }
            for (Product product : list) {
                Integer productId = product.getId();
                List<Review> allReviewByIdProduct = reviewDAO.getAllReviewByIdProduct(productId);
                product.setReviewList(allReviewByIdProduct);

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
        logger.debug("Method: getAll " + list);
        return list;
    }


    public void fillCreateStatement(PreparedStatement pstmt, Product entity) {
        try {
            pstmt.setInt(1, entity.getCategory().getId());
            pstmt.setString(2, entity.getName());
            pstmt.setString(3, entity.getDescription());
            pstmt.setBigDecimal(4, entity.getPrice());
            pstmt.setInt(5, entity.getSum());
            pstmt.setBytes(6, entity.getPicture());
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    public void fillEditStatement(PreparedStatement pstmt, Product entity) {
        try {
            pstmt.setInt(1, entity.getCategory().getId());
            pstmt.setString(2, entity.getName());
            pstmt.setString(3, entity.getDescription());
            pstmt.setBigDecimal(4, entity.getPrice());
            pstmt.setInt(5, entity.getSum());
            pstmt.setBytes(6, entity.getPicture());
            pstmt.setInt(7, entity.getId());
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    public Product getEntity(ResultSet resultSet) {
        Product product = new Product();
        try {
            int idProduct = resultSet.getInt("idProduct");
            product.setId(idProduct);
            int category = resultSet.getInt("category");
            Category byId = categoryDAO.getById(category);
            product.setCategory(byId);
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setPrice(resultSet.getBigDecimal("price"));
            product.setSum(resultSet.getInt("sum"));
            product.setPicture(resultSet.getBytes("picture"));
            List<Review> allReviewByIdProduct = reviewDAO.getAllReviewByIdProduct(idProduct);
            product.setReviewList(allReviewByIdProduct);
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        return product;
    }

    public String getCreateQuery() {
        return dbService.getQuery("create.product");
    }

    public String getDeleteQuery() {
        return dbService.getQuery("delete.product");
    }

    public String getEditQuery() {
        return dbService.getQuery("update.product");
    }

    public String getGetByIdQuery() {
        return dbService.getQuery("get.product.by.id");
    }

    public String getGetAllQuery() {
        return dbService.getQuery("get.all.product");
    }
}
