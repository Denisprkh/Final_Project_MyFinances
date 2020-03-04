package by.prokhorenko.controller.command.impl;

import by.prokhorenko.controller.command.Command;
import by.prokhorenko.controller.exception.ControllerException;
import by.prokhorenko.service.IUserService;
import by.prokhorenko.service.exception.ServiceException;
import by.prokhorenko.service.factory.ServiceFactory;
import by.prokhorenko.util.Parser;

public class Registration implements Command {

    @Override
    public String execute(String request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IUserService userService = serviceFactory.getUserService();



        String login = Parser.getValueParam(request,"login");;
        String password = Parser.getValueParam(request,"password");;

        boolean result = false;
        try {
            result = userService.registration(login, password);
        } catch (ServiceException e) {
            //write log
           return "Problems with registration, try again";
        }

        return result ? "Congratulations!You are registered !" : "User with such login is already registered!";
    }
}
