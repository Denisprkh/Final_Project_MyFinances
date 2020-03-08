package by.prokhorenko.dao.exception;

public class InvalidParameterDAOException extends  Exception{

    public InvalidParameterDAOException() {
        super();
    }

    public InvalidParameterDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParameterDAOException(String message) {
        super(message);
    }
}
