package biz.bagira.shds.eshop.controller;

import biz.bagira.shds.eshop.dao.CategoryDAO;
import biz.bagira.shds.eshop.dao.ProductDAO;
import biz.bagira.shds.eshop.dao.ReviewDAO;
import biz.bagira.shds.eshop.entity.Category;
import biz.bagira.shds.eshop.entity.Product;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Dmitriy on 09.10.2016.
 */
public class EshopServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(EshopServlet.class);

    CategoryDAO categoryDAO = new CategoryDAO();
    ProductDAO productDAO = new ProductDAO();
    ReviewDAO reviewDAO = new ReviewDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
         logger.debug(">>>>>>>>>>Category = "+category);
        if (category == null) {
            List<Category> categories = categoryDAO.getAll();
            List<Product> products = productDAO.getAll();
            request.setAttribute("categories", categories);
            request.setAttribute("products", products);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/pages/index.jsp");
            requestDispatcher.forward(request, response);
        }


        if (category != null)
        {
            logger.debug("Servlet:   category  = "+category);
            Integer idCategory = categoryDAO.getIdCategoryByType(category);
            List<Product> productList = productDAO.getAllbyCategory(idCategory);
            logger.debug("Servlet: allbyCategory = "+productList);
            request.setAttribute("listproducts",productList);
            RequestDispatcher requestDispatcher1 = getServletContext().getRequestDispatcher("/pages/product.jsp");
            requestDispatcher1.forward(request,response);
            logger.debug(">>>>>>>>>>>>>AFTER FORWARD");


        }

        logger.debug(">>>>>>>>>>>> END DOGET>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
