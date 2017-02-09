package biz.bagira.shds.controller;

import biz.bagira.shds.dao.UserDAO;
import biz.bagira.shds.entity.PasswordEntity;
import biz.bagira.shds.entity.User;
import biz.bagira.shds.service.PasswordService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
            if (passwordEntityFromDB == null) {
                request.setAttribute("error", "Invalid User name or Password please try again or create new account");
                getServletContext().getRequestDispatcher("/pages/login.jsp").forward(request, response);
            } else {
                String checkedPass = passwordService.getSecurePassword(password, passwordEntityFromDB.getSalt());
                boolean validatePassword = passwordService.validatePassword(checkedPass, passwordEntityFromDB.getPassword());
                logger.debug("checkedPass = " + checkedPass);

                if (validatePassword) {
                    //log in
                    HttpSession session = request.getSession(true);
                    Integer userId = passwordEntityFromDB.getUserId();
                    User currentUser = userDAO.getById(userId);
                    session.setAttribute("user", currentUser);
                    logger.debug("USER LOG IN: " + currentUser);
                    Object categories = request.getAttribute("categories");
                    Object products = request.getAttribute("products");
                    response.sendRedirect("/");
                }
            }
        } else {
            request.setAttribute("error", "Invalid User name or Password please try again or or create new account");
            getServletContext().getRequestDispatcher("/pages/login.jsp").forward(request, response);
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String logout = request.getParameter("logout");
        logger.debug("Logout param :" + logout);

        if (logout != null && logout.equals("true")) {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            session.removeAttribute("user");
            user = (User) session.getAttribute("user");
            response.sendRedirect("/");
        }
        else {

            getServletContext().getRequestDispatcher("/pages/login.jsp").forward(request, response);
        }

    }


}
