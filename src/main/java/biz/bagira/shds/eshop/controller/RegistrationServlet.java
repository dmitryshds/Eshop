package biz.bagira.shds.eshop.controller;

import biz.bagira.shds.eshop.dao.UserDAO;
import biz.bagira.shds.eshop.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static biz.bagira.shds.eshop.service.PasswordService.isValidString;

/**
 * Created by Dmitriy on 13.10.2016.
 */
public class RegistrationServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(RegistrationServlet.class);
    UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confpass = request.getParameter("confpass");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        if (isValidString(password) && isValidString(confpass)) {
            if (!password.equals(confpass)) {
                request.setAttribute("error", "Password value and Confirm password value not equal");
                getServletContext().getRequestDispatcher("/pages/registration.jsp").forward(request, response);
            }
        }


        if (isValidString(name) && isValidString(email) && isValidString(password)) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhoneNumber(phone);
            user.setAddress(address);
            User userFromDAO = userDAO.create(user);
            HttpSession session = request.getSession(false);
            session.setAttribute("user", userFromDAO);
            getServletContext().getRequestDispatcher("/pages/login.jsp").forward(request, response);

        } else {
            if (!isValidString(name)) {
                request.setAttribute("error", "Incorrect Name value ");
                getServletContext().getRequestDispatcher("/pages/registration.jsp").forward(request, response);
            }
            if (!isValidString(email)) {
                request.setAttribute("error", "Incorrect Email value ");
               getServletContext().getRequestDispatcher("/pages/registration.jsp").forward(request, response);
            }

            if (!isValidString(password)) {
                request.setAttribute("error", "Incorrect Password value ");
                getServletContext().getRequestDispatcher("/pages/registration.jsp").include(request, response);
            }


        }


    }



}
