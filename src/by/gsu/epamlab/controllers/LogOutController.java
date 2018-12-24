package by.gsu.epamlab.controllers;

import by.gsu.epamlab.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogOutController",urlPatterns = {"/logout"})
public class LogOutController extends AbstractServlet {
    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect(Constants.LOGIN_JSP);
    }
}
