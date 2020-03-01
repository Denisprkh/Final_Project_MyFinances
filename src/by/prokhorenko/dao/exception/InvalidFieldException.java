package by.prokhorenko.dao.exception;

public class InvalidFieldException extends Exception{
    public InvalidFieldException() {
        super();
    }


    public InvalidFieldException (String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFieldException (String message) {
        super(message);
    }


}
