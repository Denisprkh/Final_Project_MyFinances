package by.prokhorenko.dao.exception;

public class InvalidFieldDAOException extends Exception{
    public InvalidFieldDAOException() {
        super();
    }

    public InvalidFieldDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFieldDAOException(String message) {
        super(message);
    }


}
