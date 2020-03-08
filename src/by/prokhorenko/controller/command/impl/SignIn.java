package by.prokhorenko.controller.command.impl;

import by.prokhorenko.controller.command.Command;
import by.prokhorenko.controller.exception.ControllerException;
import by.prokhorenko.service.IUserService;
import by.prokhorenko.service.exception.ServiceException;
import by.prokhorenko.service.factory.ServiceFactory;
import by.prokhorenko.service.impl.UserServiceImpl;
import by.prokhorenko.util.Parser;

public class SignIn implements Command {
    @Override
    public String execute(String request) throws ControllerException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserServiceImpl userService = serviceFactory.getUserService();

        String login = Parser.getValueParam(request,"login");
        String password = Parser.getValueParam(request,"password");
        boolean result;
        try {

            result = (userService.signIn(login,password));


        } catch (ServiceException e) {
            //write log
            String mes = "Signing in error";
            throw new ControllerException(mes,e);
        }

        return result ? "You are entered! Welcome" : "Something went bad, please try again";

    }
}
