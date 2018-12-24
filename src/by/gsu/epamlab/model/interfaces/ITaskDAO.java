package by.gsu.epamlab.model.interfaces;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.enums.TaskAction;
import by.gsu.epamlab.model.exception.DAOException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface ITaskDAO {
    List<Task> getTasks(User user, String nameSelection) throws DAOException;
    void addTasks(int userId, String task, LocalDate date) throws DAOException;
    void tasksAction(List<String> tasksId, TaskAction action) throws DAOException;
    void addFileName(String fileName,String actualFileName, String tasksId) throws DAOException;
    void deleteFile(String tasksId) throws DAOException;
    String getActualFileName(String tasksId) throws DAOException;
}
