package by.prokhorenko.controller.command;

import by.prokhorenko.controller.exception.ControllerException;

public interface Command {

    String execute (String request);

}
