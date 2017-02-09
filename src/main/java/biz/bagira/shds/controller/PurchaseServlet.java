package biz.bagira.shds.controller;

import biz.bagira.shds.dao.OrderDAO;
import biz.bagira.shds.dao.ProductDAO;
import biz.bagira.shds.entity.Order;
import biz.bagira.shds.entity.Payment;
import biz.bagira.shds.entity.Product;
import biz.bagira.shds.entity.User;
import biz.bagira.shds.service.PasswordService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmitriy on 28.10.2016.
 */
public class PurchaseServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(PurchaseServlet.class);
    ProductDAO productDAO = new ProductDAO();
    OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String payment = request.getParameter("payment");
        logger.debug("PurchaseServlet  payment: " + payment);
        if (payment == null) {
            Enumeration parameterNames = request.getParameterNames();
            Map<Product, Integer> productChoice = new HashMap<Product, Integer>();
            Integer key = null;
            Integer value = null;

            while (parameterNames.hasMoreElements()) {
                String keyId = (String) parameterNames.nextElement();
                String valueId = request.getParameter(keyId);
                if (PasswordService.isNumber(keyId) && PasswordService.isNumber(valueId)) {
                    key = Integer.parseInt(keyId);
                    value = Integer.parseInt(valueId);
                }
                Product product = productDAO.getById(key);
                if (product.getSum() >= value) {
                    if (value != 0) {
                        productChoice.put(product, value);
                    }
                } else {
                    request.setAttribute("error", "Product: " + product.getName() + " not enough on stock");
                    getServletContext().getRequestDispatcher("/pages/shopping.jsp").forward(request, response);
                    return;
                }
            }
            logger.debug("productChoice = " + productChoice);
            request.setAttribute("choise", productChoice);
            getServletContext().getRequestDispatcher("/pages/pay.jsp").forward(request, response);
        } else {

            Enumeration parameterNames = request.getParameterNames();
            Map<Product, Integer> productChoice = new HashMap<Product, Integer>();
            Integer key = null;
            Integer value = null;
            Order order = new Order();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            order.setUser(user);
            Map<Product, Integer> orderProductChoice = order.getProductChoice();

            while (parameterNames.hasMoreElements()) {
                String keyId = (String) parameterNames.nextElement();
                String valueId = request.getParameter(keyId);
                if (keyId.equals("cash")) {
                    order.setPayment(Payment.getPayment(valueId));
                } else if (keyId.startsWith("id=")) {
                    Product productById = null;
                    int parseInt;
                    keyId = keyId.substring(3, keyId.length());
                    if (PasswordService.isNumber(keyId)) {
                        parseInt = Integer.parseInt(keyId);
                        productById = productDAO.getById(parseInt);

                    }
                    if (PasswordService.isNumber(valueId)) {
                        int requiredSum = Integer.parseInt(keyId);
                        int productbyIdSum = productById.getSum();

                        if (productbyIdSum > 1) {
                            orderProductChoice.put(productById, requiredSum);
                            productById.setSum(productbyIdSum - 1);
                            productDAO.edit(productById);
                        }
                    }


                } else if (keyId.equals("comment")) {
                    order.setComment(valueId);

                } else if (keyId.equals("totalprice")) {

                    if (PasswordService.isNumber(valueId)) {
                        double parseDouble = Double.parseDouble(valueId);
                        order.setTotalPrice(new BigDecimal(parseDouble));
                    }
                }


            }
            logger.debug("ORDER IS:  " + order);
            Order createdOrder = orderDAO.create(order);
            Integer id = createdOrder.getId();
            request.setAttribute("order", id);
            getServletContext().getRequestDispatcher("/pages/success.jsp").forward(request, response);


        }

    }

}
