package by.gsu.epamlab.model.exception;

public class InvalidLoginOrPasswordException extends DAOException {
    public InvalidLoginOrPasswordException(String message) {
        super(message);
    }
}
