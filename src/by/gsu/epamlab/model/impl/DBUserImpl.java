package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.connection.ConnectionPool;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exception.DAOException;
import by.gsu.epamlab.model.exception.InvalidLoginOrPasswordException;
import by.gsu.epamlab.model.interfaces.IUserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUserImpl implements IUserDAO{
    private static final String SELECT_LOGIN =
            "SELECT login FROM users WHERE login = ?;";
    private static final String SELECT_LOGIN_AND_PASS =
            "SELECT * FROM users WHERE login = ?;";
    private static final String INSERT_USER =
            "INSERT INTO users(login,password) VALUES(?, ?);";
    private static final int LOGIN_INDEX = 1;
    private static final int PASSWORD_INDEX = 2;

    Connection connection = ConnectionPool.getInstance().getConnection();

    @Override
    public User getUser(String login, String password) throws DAOException {
        if(login.equals(Constants.EMPTY_STRING)) {
            throw new InvalidLoginOrPasswordException(Constants.EMPTY_LOGIN);
        }
        if (password.equals(Constants.EMPTY_STRING)) {
            throw new InvalidLoginOrPasswordException(Constants.EMPTY_PASSWORD);
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOGIN_AND_PASS);
            preparedStatement.setString(LOGIN_INDEX,login);
            ResultSet rs = preparedStatement.executeQuery();
            if(!rs.next()){
                throw new InvalidLoginOrPasswordException(
                        Constants.USER_NOT_FOUND);
            }
            if (rs.getString(Constants.KEY_PASSWORD).equals(password)) {
                return new User(rs.getInt(Constants.KEY_ID), login);
            }   else {
                throw new InvalidLoginOrPasswordException(
                        Constants.INVALID_PASSWORD);
            }
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
    public void addUser(String login, String password) throws DAOException {
        if(Constants.EMPTY_STRING.equals(login)){
            throw new InvalidLoginOrPasswordException(
                    Constants.EMPTY_LOGIN);
        }
        if(password.equals(Constants.EMPTY_STRING)){
            throw new InvalidLoginOrPasswordException(
                    Constants.EMPTY_PASSWORD);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOGIN);
            preparedStatement.setString(LOGIN_INDEX,login);
            synchronized (this) {
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    throw new InvalidLoginOrPasswordException(Constants.USER_EXIST);
                }
                rs.close();
                preparedStatement.close();
                preparedStatement = connection.prepareStatement(INSERT_USER);
                preparedStatement.setString(LOGIN_INDEX, login);
                preparedStatement.setString(PASSWORD_INDEX, password);
                preparedStatement.execute();

                preparedStatement.close();
            }
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
}

