package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exception.DAOException;
import by.gsu.epamlab.model.exception.InvalidLoginOrPasswordException;
import by.gsu.epamlab.model.interfaces.IUserDAO;

import java.util.HashMap;
import java.util.Map;

public class HardcodedUserImpl implements IUserDAO {
    private static Map<String, String> users = new HashMap<String, String>();

    @Override
    public User getUser(String login, String password) throws DAOException {
        if(login.equals(Constants.EMPTY_STRING)) {
            throw new InvalidLoginOrPasswordException(Constants.EMPTY_LOGIN);
        }
        if(password.equals(Constants.EMPTY_PASSWORD)) {
            throw new InvalidLoginOrPasswordException(Constants.EMPTY_PASSWORD);
        }
        if(users.containsKey(login)) {
            if(users.get(login).equals(password)){
                return new User(login);
            } else {
                throw new InvalidLoginOrPasswordException(Constants.INVALID_PASSWORD);
            }
        }else {
            throw new InvalidLoginOrPasswordException(Constants.USER_NOT_FOUND);
        }

    }

    @Override
    public void addUser(String login, String password) throws DAOException{
        if(login.equals(Constants.EMPTY_LOGIN)) {
            throw new InvalidLoginOrPasswordException(Constants.EMPTY_LOGIN);
        }
        if(password.equals(Constants.EMPTY_PASSWORD)) {
            throw new InvalidLoginOrPasswordException(Constants.EMPTY_PASSWORD);
        }
        synchronized (users) {
            if (users.containsKey(login)) {
                throw new InvalidLoginOrPasswordException(Constants.USER_EXIST);
            } else {
                users.put(login, password);
            }
        }
    }
}
