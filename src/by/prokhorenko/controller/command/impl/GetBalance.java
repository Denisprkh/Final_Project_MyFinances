package by.prokhorenko.controller.command.impl;

import by.prokhorenko.controller.command.Command;
import by.prokhorenko.controller.exception.ControllerException;
import by.prokhorenko.service.IUserService;
import by.prokhorenko.service.exception.ServiceException;
import by.prokhorenko.service.factory.ServiceFactory;
import by.prokhorenko.util.Parser;
import java.math.BigDecimal;

public class GetBalance implements Command {
    @Override
    public String execute(String request) throws ControllerException{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IUserService userService = serviceFactory.getUserService();

        String login = Parser.getValueParam(request,"login");
        BigDecimal balance;
        try {
            balance = userService.getBalance(login);
        } catch (ServiceException e) {
            String mes = "Getting balance error";
            //write log
            throw new ControllerException(mes,e);
        }
        return balance.toString();
    }
}
