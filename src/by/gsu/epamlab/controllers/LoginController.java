package by.gsu.epamlab.controllers;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exception.DAOException;
import by.gsu.epamlab.model.exception.InvalidLoginOrPasswordException;
import by.gsu.epamlab.model.factories.UserFactory;
import by.gsu.epamlab.model.interfaces.IUserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController",urlPatterns = {"/login"})
public class LoginController extends AbstractServlet {
    @Override
    protected void performTask(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        try {
            String login = request.getParameter(Constants.KEY_LOGIN);
            String password = request.getParameter(Constants.KEY_PASSWORD);

            IUserDAO userDAO = UserFactory.getClassFromFactory(Constants.DB);
            User user = userDAO.getUser(login.trim(), password);
            HttpSession session = request.getSession();
            session.setAttribute(Constants.USER, user);
            request.getRequestDispatcher(Constants.INDEX_JSP).forward(request,response);
        } catch (DAOException e) {
            jumpError(e,request,response);
        }
    }
    private void jumpError(DAOException e, HttpServletRequest request,
                           HttpServletResponse response)
        throws ServletException, IOException {
        request.setAttribute(Constants.KEY_ERROR_MASSAGE, e.getMessage());
        request.getRequestDispatcher(Constants.LOGIN_JSP).forward(request,response);
    }
}


