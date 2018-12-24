package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.connection.ConnectionPool;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.enums.Sections;
import by.gsu.epamlab.model.enums.TaskAction;
import by.gsu.epamlab.model.exception.DAOException;
import by.gsu.epamlab.model.interfaces.ITaskDAO;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskImplDB implements ITaskDAO {

    private static final String INSERT_TASK =
            "INSERT INTO tasks(userId, description, dateCompletion, fixed, recycleBin) VALUES(?, ?, ?, 'false','false');";

    private Connection connection;

    public TaskImplDB() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }



    @Override
    public void addTasks(int userId, String task, LocalDate date) throws DAOException{
        if(task.equals("") || date.equals("")){
            throw new DAOException("Field with new task is empty");
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, task);
            preparedStatement.setDate(3, Date.valueOf(date));
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public List<Task> getTasks(User user, String nameSelection) throws DAOException {
        List<Task> tasks = new ArrayList<Task>();
        Sections sections = Sections.valueOf(nameSelection);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sections.getQueryTail());
            preparedStatement.setInt(1, user.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tasks.add(new Task(
                        resultSet.getInt(Constants.KEY_ID_TASK),
                        resultSet.getString(Constants.KEY_DESCRIPTION),
                        resultSet.getDate(Constants.KEY_DATE).toLocalDate(),
                        resultSet.getString(Constants.KEY_FILE_NAME),
                        resultSet.getString(Constants.KEY_ACTUAL_FILE_NAME)));
            }
            return tasks;
        } catch (SQLException e) {
            throw new DAOException(e);
        }   finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void tasksAction(List<String> tasksId, TaskAction action) throws DAOException {
        try {
            for(String taskId : tasksId) {
                PreparedStatement pr = connection.prepareStatement(action.getQueryTail());
                pr.setInt(1, Integer.parseInt(taskId));
                pr.execute();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void addFileName(String fileName, String actualFileName, String tasksId) throws DAOException {
        try {
            PreparedStatement pr = connection.prepareStatement("UPDATE tasks SET fileName = ?, actualFileName = ? WHERE taskId = ?;");
            pr.setString(1,fileName);
            pr.setString(2,actualFileName);
            pr.setInt(3, Integer.parseInt(tasksId));
            pr.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteFile(String taskId) throws DAOException {
        try {
            PreparedStatement pr = connection.prepareStatement("UPDATE tasks SET fileName = '' , actualFileName = '' WHERE taskId = ?;");
            pr.setInt(1, Integer.parseInt(taskId));
            pr.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public String getActualFileName(String taskId) throws DAOException {
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT actualFileName FROM tasks WHERE taskId = ?;");
            pr.setInt(1, Integer.parseInt(taskId));
            ResultSet rs = pr.executeQuery();
            if (!rs.next()) {
                return "";
            }
            return rs.getString("actualFileName");

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
