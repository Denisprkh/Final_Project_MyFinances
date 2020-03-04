package by.prokhorenko.controller;

import by.prokhorenko.controller.command.Command;
import by.prokhorenko.controller.exception.ControllerException;
import by.prokhorenko.util.Parser;

public class Controller {

    private CommandProvider commandProvider = new CommandProvider();

    public String executeTask(String request) throws ControllerException {
        String commandName = request.substring(0, request.indexOf(Parser.DELIMITER));
        Command command = commandProvider.getCommand(commandName);
        String response = command.execute(request);

        return response;
    }
}
