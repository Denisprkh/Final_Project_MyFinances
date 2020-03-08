package by.prokhorenko.controller.command.impl;

import by.prokhorenko.controller.command.Command;
import by.prokhorenko.controller.exception.ControllerException;

public class LogOut implements Command {

    @Override
    public String execute(String request) throws ControllerException {
        String response = "You are left";
        return response;
    }
}
