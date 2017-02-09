package biz.bagira.shds.controller;

import biz.bagira.shds.dao.ProductDAO;
import biz.bagira.shds.entity.Product;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitriy on 09.10.2016.
 */
public class ImageServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ImageServlet.class);
   ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
           logger.debug("ImageServlet id = " + id);
        if (id != null) {
            Product product = productDAO.getById(Integer.parseInt(id));
            response.setContentType("image/jpg");
            response.getOutputStream().write(product.getPicture());
        }
    }
}
