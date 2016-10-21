package biz.bagira.shds.eshop.dao;

import biz.bagira.shds.eshop.entity.PasswordEntity;
import biz.bagira.shds.eshop.entity.User;
import biz.bagira.shds.eshop.service.PasswordService;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Arrays;

/**
 * Created by Dmitriy on 11.10.2016.
 */
public class UserDAO extends AbstractDAOImpl<User> {

    final static Logger logger = Logger.getLogger(UserDAO.class);
    PasswordService passwordService = new PasswordService();

    public void fillCreateStatement(PreparedStatement pstmt, User entity) {
        try {
            pstmt.setString(1, entity.getName());
            String password = entity.getPassword();
            byte[] salt = passwordService.getSalt();
            String securePassword = passwordService.getSecurePassword(password, salt);
            logger.debug(">>>>>>securePassword = " + securePassword);
            logger.debug(">>>>>>salt = " + Arrays.toString(salt));
            pstmt.setString(2, securePassword);
            pstmt.setBytes(3, salt);
            pstmt.setString(4, entity.getEmail());
            pstmt.setString(5, entity.getAddress());
            pstmt.setString(6, entity.getPhoneNumber());
            pstmt.setDate(7, new Date(System.currentTimeMillis()));
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    public void fillEditStatement(PreparedStatement pstmt, User entity) {
        try {
            String password = entity.getPassword();
            byte[] salt = passwordService.getSalt();
            String securePassword = passwordService.getSecurePassword(password, salt);
            pstmt.setString(1, securePassword);
            pstmt.setBytes(2, salt);
            pstmt.setString(3, entity.getEmail());
            pstmt.setString(4, entity.getAddress());
            pstmt.setString(5, entity.getPhoneNumber());
            pstmt.setInt(6, entity.getId());
        } catch (SQLException e) {
            logger.debug(e.getMessage());

        }

    }

    public User getEntity(ResultSet resultSet) {
        User user = new User();

        try {
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setAddress(resultSet.getString("adress"));
            user.setPhoneNumber(resultSet.getString("phone"));
            user.setDateRegistration(resultSet.getDate("date"));
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }

        return user;
    }

    public PasswordEntity getPasswordEntityFromDB(String name) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        PasswordEntity passwordEntity = new PasswordEntity();
        try {

            connection = dbService.getConnection();
            String query = getPasswordEntity();
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, name);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                passwordEntity.setUserId(resultSet.getInt("id"));
                passwordEntity.setPassword(resultSet.getString("password"));
                passwordEntity.setSalt(resultSet.getBytes("salt"));

                logger.debug("Method: getPasswordEntityFromDB " + passwordEntity);
                return passwordEntity;
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

    public String getCreateQuery() {
        return dbService.getQuery("create.user");
    }

    public String getDeleteQuery() {
        return dbService.getQuery("delite.user");
    }

    public String getEditQuery() {
        return dbService.getQuery("update.user");
    }

    public String getGetByIdQuery() {
        return dbService.getQuery("get.user.by.id");
    }

    public String getGetAllQuery() {
        return dbService.getQuery("get.all.user");
    }

    public String getPasswordEntity() {
        return dbService.getQuery("get.id.password.and.salt.by.name");
    }
}
