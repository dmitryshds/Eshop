package biz.bagira.shds.eshop.controller;

import biz.bagira.shds.eshop.dao.ProductDAO;
import biz.bagira.shds.eshop.entity.Product;
import biz.bagira.shds.eshop.entity.User;
import biz.bagira.shds.eshop.service.PasswordService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static biz.bagira.shds.eshop.service.PasswordService.isValidString;

/**
 * Created by Dmitriy on 26.10.2016.
 */
public class ShopingCartServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ShopingCartServlet.class);
     ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.debug("ShopingCartServlet");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        logger.debug("ShopingCartServlet user = " + user);


        if (user == null) {
            response.sendRedirect("/login");
        } else {

            String idProduct = request.getParameter("id");
            String category = request.getParameter("category");
            if (isValidString(idProduct)) {
                int parseInt = Integer.parseInt(idProduct);
                boolean addToList = user.addIdProductToList(parseInt);
                if (!addToList)
                {
                    request.setAttribute("error","You add this item to the shopping card");
                }
                session.removeAttribute("user");
                session.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/pages/product?category=" + category).forward(request, response);

            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = new ArrayList<Product>();
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String reviewId = request.getParameter("revid");
        logger.debug("ShopingCartServlet reviewId = "+reviewId );

        if (reviewId != null)
        {
            if(PasswordService.isNumber(reviewId)){
                int productId = Integer.parseInt(reviewId);
                Product product = productDAO.getById(productId);
                request.setAttribute("product",product);
                getServletContext().getRequestDispatcher("/pages/review.jsp").forward(request,response);

            }

        }

        if (user != null) {
            List<Integer> productIdList = user.getProductIdList();
            for (Integer id : productIdList) {
                Product product = productDAO.getById(id);
                logger.debug("Buy product = " + product);
                if (product != null) {
                    productList.add(product);
                }
            }
            request.setAttribute("listprod", productList);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/pages/shopping.jsp");
            requestDispatcher.forward(request, response);
        }
        else {
            response.sendRedirect("/login");
        }


    }
}
