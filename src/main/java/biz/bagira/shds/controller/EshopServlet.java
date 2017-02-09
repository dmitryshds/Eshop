package biz.bagira.shds.controller;

import biz.bagira.shds.dao.CategoryDAO;
import biz.bagira.shds.dao.ProductDAO;
import biz.bagira.shds.entity.Category;
import biz.bagira.shds.entity.Product;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       process(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             process(request,response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        String category = request.getParameter("category");


                logger.debug("Category = " + category);
                if (category == null) {
                    List<Category> categories = categoryDAO.getAll();
                    List<Product> products = productDAO.getAll();
                    request.setAttribute("categories", categories);
                    request.setAttribute("products", products);
                    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/pages/index.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    Integer idCategory = categoryDAO.getIdCategoryByType(category);
                    List<Product> productList = productDAO.getAllbyCategory(idCategory);
                    request.setAttribute("listproducts", productList);
                    RequestDispatcher requestDispatcher1 = getServletContext().getRequestDispatcher("/pages/product.jsp");
                    requestDispatcher1.forward(request, response);


                }



    }
}
