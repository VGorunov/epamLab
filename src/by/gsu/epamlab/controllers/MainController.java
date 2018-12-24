package by.gsu.epamlab.controllers;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exception.DAOException;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.model.interfaces.ITaskDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@WebServlet(name = "MainController", urlPatterns = {"/todo/main"})
public class MainController extends AbstractServlet {

    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute(Constants.USER);
            LocalDate dateCompletion = LocalDate.parse(req.getParameter("dateCompletion"));
            String description = req.getParameter("description");

            ITaskDAO taskDAO = TaskFactory.getClassFromFactory();
            int userId = user.getUserId();

            taskDAO.addTasks(userId, description, dateCompletion);

            resp.sendRedirect(Constants.INDEX_JSP);
        }catch(DAOException e){
            jumpError(e, req, resp);
        }
    }
    private void jumpError(DAOException e, HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(Constants.KEY_ERROR_MASSAGE, e.getMessage());
        request.getRequestDispatcher(Constants.INDEX_JSP).forward(request,response);
    }
 }
