package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.model.exception.DAOException;
import by.gsu.epamlab.model.impl.DBUserImpl;
import by.gsu.epamlab.model.impl.HardcodedUserImpl;
import by.gsu.epamlab.model.interfaces.IUserDAO;

public class UserFactory {
    private UserFactory() {}

    public static IUserDAO getClassFromFactory(String type) throws DAOException {
        switch (type) {
            case "ram":
                return new HardcodedUserImpl();
            case "db":
                return new DBUserImpl();
                default:
                    throw new DAOException("type of DAO is not found");
        }
    }
}

