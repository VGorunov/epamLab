package by.gsu.epamlab.controllers;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.enums.Sections;
import by.gsu.epamlab.model.exception.DAOException;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TaskServlet",urlPatterns = {"/todo/tasks"})
public class TaskServlet extends AbstractServlet {
    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ITaskDAO taskDAO = TaskFactory.getClassFromFactory();

        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute(Constants.USER);
            String jspSection = req.getParameter(Constants.KEY_SECTION);
            if(jspSection == null){
                jspSection = Sections.TODAY.name();
            }

            List<Task> tasks = taskDAO.getTasks(user, jspSection);

            JSONArray jsonArray = new JSONArray(tasks);
            resp.getWriter().println(jsonArray);

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}