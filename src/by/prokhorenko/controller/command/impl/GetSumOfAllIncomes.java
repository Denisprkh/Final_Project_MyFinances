package by.prokhorenko.controller.command.impl;

import by.prokhorenko.controller.command.Command;
import by.prokhorenko.controller.exception.ControllerException;
import by.prokhorenko.service.IUserService;
import by.prokhorenko.service.exception.ServiceException;
import by.prokhorenko.service.factory.ServiceFactory;
import by.prokhorenko.util.Parser;

import java.math.BigDecimal;

public class GetSumOfAllIncomes implements Command {


    @Override
    public String execute(String request) {
        return null;
    }
}
