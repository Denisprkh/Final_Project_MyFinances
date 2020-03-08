package by.prokhorenko.controller.command.impl;

import by.prokhorenko.bean.transaction.TransactionType;
import by.prokhorenko.bean.user.User;
import by.prokhorenko.controller.command.Command;
import by.prokhorenko.controller.exception.ControllerException;
import by.prokhorenko.service.IUserService;
import by.prokhorenko.service.exception.ServiceException;
import by.prokhorenko.service.factory.ServiceFactory;
import by.prokhorenko.util.Parser;

import java.math.BigDecimal;

public class GetSumOfTransactionsOfCertainType implements Command {
    @Override
    public String execute(String request) throws ControllerException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IUserService userService = serviceFactory.getUserService();

        String login = Parser.getValueParam(request,"login");
        String type = Parser.getValueParam(request, "transactionType");
        StringBuilder st = new StringBuilder();
        try {
            User user = userService.get(login);
            BigDecimal sum = userService.getSumOfAllUsersTransactionsOfCertainType(user, TransactionType.valueOf(type));
            st.append(sum);
        } catch (ServiceException e) {
            //write log
           String mes = "GetSumOfTransactionsOfCertainType error";
           throw new ControllerException(mes,e);
        }

        return st.toString();
    }
}
