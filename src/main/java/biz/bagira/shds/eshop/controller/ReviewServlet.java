package biz.bagira.shds.eshop.controller;

import biz.bagira.shds.eshop.dao.ProductDAO;
import biz.bagira.shds.eshop.dao.ReviewDAO;
import biz.bagira.shds.eshop.entity.Product;
import biz.bagira.shds.eshop.entity.Review;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static biz.bagira.shds.eshop.service.PasswordService.isNumber;
import static biz.bagira.shds.eshop.service.PasswordService.isValidString;

/**
 * Created by Dmitriy on 23.11.2016.
 */
public class ReviewServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ReviewServlet.class);
    ReviewDAO reviewDAO = new ReviewDAO();
    ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String parameter = request.getParameter("new-review");
        String reviewId = request.getParameter("review_id");
        logger.debug("ReviewServlet new-review: " + parameter);
        logger.debug("ReviewServlet reviewId: " + reviewId);

        if (isValidString(parameter)) {
            if (isNumber(reviewId)) {
                int productId = Integer.parseInt(reviewId);
                Review newReview = new Review();
                newReview.setReview(parameter);
                Product product = new Product();
                product.setId(productId);
                reviewDAO.createReview(newReview, product);

                Product productFromDAO = productDAO.getById(productId);
                request.setAttribute("product", productFromDAO);
            }

            getServletContext().getRequestDispatcher("/pages/review.jsp").forward(request, response);
        } else {
            if (isNumber(reviewId)) {
                int productId = Integer.parseInt(reviewId);
                Product productFromDAO = productDAO.getById(productId);
                request.setAttribute("product", productFromDAO);
                request.setAttribute("error", "you can't add empty review");
                getServletContext().getRequestDispatcher("/pages/review.jsp").forward(request, response);
            }
        }


    }
}
