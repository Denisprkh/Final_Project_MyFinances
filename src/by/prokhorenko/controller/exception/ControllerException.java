package by.prokhorenko.controller.exception;

public class ControllerException extends Exception{

    public ControllerException() {
        super();
    }



    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerException(String message) {
        super(message);
    }
}
