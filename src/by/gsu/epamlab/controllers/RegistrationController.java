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
import java.io.IOException;

@WebServlet(name = "RegistrationController",urlPatterns = {"/registration"})
public class RegistrationController extends AbstractServlet {
    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String login = req.getParameter(Constants.KEY_LOGIN);
            String password = req.getParameter(Constants.KEY_PASSWORD);
            login = login.trim();
            if(login == null || password == null) {
                throw new InvalidLoginOrPasswordException(Constants.EMPY_LOGIN_OR_PASSWORD);
            }
            UserFactory.getClassFromFactory(Constants.DB).addUser(login, password);
            resp.sendRedirect(Constants.LOGIN_JSP);
        } catch (DAOException e) {
                jumpError(e, req, resp);
        }
    }

    private void jumpError(DAOException e, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Constants.KEY_ERROR_MASSAGE, e.getMessage());
        request.getRequestDispatcher(Constants.REGISTRATION_JSP).forward(request, response);
    }
}

