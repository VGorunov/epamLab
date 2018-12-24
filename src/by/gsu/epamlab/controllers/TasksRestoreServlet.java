package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.enums.TaskAction;
import by.gsu.epamlab.model.exception.DAOException;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TasksRestoreServlet",urlPatterns = {"/todo/restore"})
public class TasksRestoreServlet extends AbstractServlet {
    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ITaskDAO taskDAO = TaskFactory.getClassFromFactory();

        try {
            taskDAO.tasksAction(parseJson(req), TaskAction.RESTORE);
        } catch (JSONException e) {
            // crash and burn
            throw new IOException("Error parsing JSON request string");
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }
}
