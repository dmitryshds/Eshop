package biz.bagira.shds.eshop.dao;

import biz.bagira.shds.eshop.entity.Product;
import biz.bagira.shds.eshop.entity.Review;
import biz.bagira.shds.eshop.service.DBService;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy on 06.10.2016.
 */
public class ReviewDAO {

    final static Logger logger = Logger.getLogger(ReviewDAO.class);

    DBService dbService = new DBService();

    public Integer createReview(Review review, Product product) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            connection = dbService.getConnection();
            String query = dbService.getQuery("create.review");
            pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            Integer productId = product.getId();
            pstmt.setInt(1, productId);
            pstmt.setString(2, review.getReview());
            pstmt.setDate(3, new Date(System.currentTimeMillis()));
            pstmt.executeUpdate();
            resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()) {

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

    public List<Review> getAllReviewByIdProduct(Integer idProduct) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        List<Review> rezult = new ArrayList<Review>();
        try {
            connection = dbService.getConnection();
            String query = dbService.getQuery("get.reviews.by.id");
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idProduct);
            resultSet = pstmt.executeQuery();
           while (resultSet.next()) {
                Review review = new Review();
                review.setId(resultSet.getInt("idreviews"));
                review.setReview(resultSet.getString("review"));
                review.setDateReview(resultSet.getDate("date_review"));
                rezult.add(review);
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

        return rezult;
    }

    public boolean deliteReview(Integer idReview) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        List<Review> rezult = new ArrayList<Review>();
        try {
            connection = dbService.getConnection();
            String query = dbService.getQuery("delite.review");
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idReview);
           return pstmt.execute();


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

        return false;
    }


}
