package by.prokhorenko.dao.exception;

public class InvalidParameterException  extends  Exception{

    public InvalidParameterException() {
        super();
    }


    public InvalidParameterException (String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParameterException (String message) {
        super(message);
    }
}
