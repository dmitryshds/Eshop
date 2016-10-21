package biz.bagira.shds.eshop.controller;

import biz.bagira.shds.eshop.dao.UserDAO;
import biz.bagira.shds.eshop.entity.PasswordEntity;
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
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by Dmitriy on 11.10.2016.
 */
public class LoginServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(LoginServlet.class);

    UserDAO userDAO = new UserDAO();
    PasswordService passwordService = new PasswordService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (isValidString(name) && isValidString(password)) {
            PasswordEntity passwordEntityFromDB = userDAO.getPasswordEntityFromDB(name);
            logger.debug("passwordEntityFromDB = "+passwordEntityFromDB);
            if (passwordEntityFromDB == null) {
                request.setAttribute("error", "Invalid User name or Password please try again or create new account");
                getServletContext().getRequestDispatcher("/pages/login.jsp").forward(request, response);
            } else {

                String checkedPass = passwordService.getSecurePassword(password, passwordEntityFromDB.getSalt());

                boolean validatePassword = passwordService.validatePassword(checkedPass, passwordEntityFromDB.getPassword());
                logger.debug(">>>>>>>>equals = " + validatePassword);
                logger.debug("passwordEntityFromDBPassword = " + passwordEntityFromDB.getPassword());
                logger.debug("checkedPass = " + checkedPass);

                if (validatePassword) {
                    //log in
                    HttpSession session = request.getSession(true);

                    Integer userId = passwordEntityFromDB.getUserId();
                    logger.debug("user Id from DB = "+userId);
                    User currentUser = userDAO.getById(userId);
                    session.setAttribute("user", currentUser);

                    logger.debug(">>>>USER LOG IN<<<<   "+currentUser);
                    Object categories = request.getAttribute("categories");
                    Object products = request.getAttribute("products");
                    logger.debug("categories = " + categories + " " + "products = " + products);
                    response.sendRedirect("/");
                }
            }
        } else {
            request.setAttribute("error", "Invalid User name or Password please try again or or create new account");
            getServletContext().getRequestDispatcher("/pages/login.jsp").forward(request, response);
        }


        logger.debug("<<<<<<< LoginServlet name = " + name + " Passw = " + password);


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.debug(">>>>DO GET");
        getServletContext().getRequestDispatcher("/pages/login.jsp").forward(request, response);

    }


    private boolean isValidString(String s) {
        if (s == null) return false;
        if (s.trim().isEmpty()) return false;
        return true;
    }


}
