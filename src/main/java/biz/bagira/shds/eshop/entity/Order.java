package biz.bagira.shds.eshop.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmitriy on 31.10.2016.
 */
public class Order extends AbstractEntity {
    private User user;
    private Map<Product, Integer> productChoice;
    private BigDecimal totalPrice;
    private Payment payment;
    private String comment;
    private boolean performed;
    private Date data;

    public Order() {
        this.productChoice = new HashMap<Product, Integer>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Product, Integer> getProductChoice() {
        return productChoice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isPerformed() {
        return performed;
    }

    public void setPerformed(boolean performed) {
        this.performed = performed;
    }


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", productChoice=" + productChoice +
                ", totalPrice=" + totalPrice +
                ", payment=" + payment +
                ", comment='" + comment + '\'' +
                ", performed=" + performed +
                ", data=" + data +
                '}';
    }
}
