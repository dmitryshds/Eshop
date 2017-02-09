package biz.bagira.shds.dao;

import biz.bagira.shds.entity.Order;
import biz.bagira.shds.entity.Payment;
import biz.bagira.shds.entity.User;
import biz.bagira.shds.entity.Product;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.sql.Date;

/**
 * Created by Dmitriy on 31.10.2016.
 */
public class OrderDAO extends AbstractDAOImpl<Order> {
    final static Logger logger = Logger.getLogger(OrderDAO.class);
    ProductDAO productDAO = new ProductDAO();
    UserDAO userDAO = new UserDAO();

    public void fillCreateStatement(PreparedStatement pstmt, Order entity) {
        try {
            pstmt.setInt(1, entity.getUser().getId());
            Map<Product, Integer> productChoice = entity.getProductChoice();
            StringBuffer stringBuffer = new StringBuffer();
            for (Map.Entry<Product, Integer> entry : productChoice.entrySet()) {
                Product key = entry.getKey();
                Integer value = entry.getValue();
                stringBuffer.append("id=" + key.getId() + "&value=" + value + ";");
            }
            pstmt.setString(2, stringBuffer.toString());
            pstmt.setBigDecimal(3, entity.getTotalPrice());
            Payment payment = entity.getPayment();
            pstmt.setString(4, payment.toString());


            pstmt.setString(5, entity.getComment());
            pstmt.setBoolean(6, entity.isPerformed());
            pstmt.setDate(7, new Date(System.currentTimeMillis()));
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    public void fillEditStatement(PreparedStatement pstmt, Order entity) {

        try {
            pstmt.setInt(1, entity.getUser().getId());
            Map<Product, Integer> productChoice = entity.getProductChoice();
            StringBuffer stringBuffer = new StringBuffer();
            for (Map.Entry<Product, Integer> entry : productChoice.entrySet()) {
                Product key = entry.getKey();
                Integer value = entry.getValue();
                stringBuffer.append("id=" + key.getId() + "&value=" + value + ";");
            }
            pstmt.setString(2, stringBuffer.toString());
            pstmt.setBigDecimal(3, entity.getTotalPrice());
            pstmt.setString(4, entity.getPayment().toString());
            pstmt.setString(5, entity.getComment());
            pstmt.setBoolean(6, entity.isPerformed());
            pstmt.setInt(7,entity.getId());

        } catch (SQLException e) {
            logger.debug(e.getMessage());

        }


    }

    public Order getEntity(ResultSet resultSet) {
        Order order = new Order();
        try {
            order.setId(resultSet.getInt("id"));
            int userId = resultSet.getInt("user");
            User user = userDAO.getById(userId);
            order.setUser(user);
            Map<Product, Integer> productChoice = order.getProductChoice();
            String purchase = resultSet.getString("purchase");
            String[] split = purchase.split(";");
            String id;
            String value;
            for (String s : split) {
                String[] split1 = s.split("&");
                id = split1[0].replace("id=", "");
                value = split1[1].replace("value=", "");
                if (isNumber(id) && isNumber(value)) {
                    int productId = Integer.parseInt(id);
                    int values = Integer.parseInt(value);
                    Product product = productDAO.getById(productId);
                    productChoice.put(product, values);
                }
            }
            order.setTotalPrice(resultSet.getBigDecimal("total"));
            order.setPayment(Payment.getPayment(resultSet.getString("payments")));
            order.setComment(resultSet.getString("comment"));
            order.setPerformed(resultSet.getBoolean("performed"));
            order.setData(resultSet.getDate("data"));


        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }


        return order;
    }

    public String getCreateQuery() { return dbService.getQuery("create.order"); }

    public String getDeleteQuery() {
        return dbService.getQuery("delite.order");
    }

    public String getEditQuery() {
        return dbService.getQuery("update.order");
    }

    public String getGetByIdQuery() {
        return dbService.getQuery("get.order.by.id");
    }

    public String getGetAllQuery() {
        return dbService.getQuery("get.all.orders");
    }

    private boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }
}
