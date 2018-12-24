package by.gsu.epamlab.model.interfaces;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exception.DAOException;

public interface IUserDAO {
    User getUser(String login, String password) throws DAOException;
    void addUser(String login, String password) throws DAOException;
}
