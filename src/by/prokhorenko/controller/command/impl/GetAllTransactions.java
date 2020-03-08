package by.prokhorenko.controller.command.impl;

import by.prokhorenko.bean.transaction.Transaction;
import by.prokhorenko.bean.user.User;
import by.prokhorenko.controller.command.Command;
import by.prokhorenko.controller.exception.ControllerException;
import by.prokhorenko.dao.exception.InvalidFieldDAOException;
import by.prokhorenko.dao.exception.InvalidParameterDAOException;
import by.prokhorenko.service.ITransactionService;
import by.prokhorenko.service.IUserService;
import by.prokhorenko.service.exception.ServiceException;
import by.prokhorenko.service.factory.ServiceFactory;
import by.prokhorenko.util.Parser;
import by.prokhorenko.util.convertor.TransactionConverter;

import java.util.ArrayList;

public class GetAllTransactions implements Command {
    @Override
    public String execute(String request) throws ControllerException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ITransactionService transactionService = serviceFactory.getTransactionService();
        IUserService userService = serviceFactory.getUserService();

        String login = Parser.getValueParam(request,"login");

        StringBuilder str = new StringBuilder();

        User user = null;
        try {
            user = userService.get(login);
            ArrayList<Transaction> allTransactions = transactionService.getAllUsersTransactions(user);
            if(allTransactions.size() == 0){
                str.append("You have't got any transactions");
            }else {
                for (Transaction transaction : allTransactions) {
                    str.append(TransactionConverter.convertTransactionToPrintableString(transaction) + "\n");
                }
            }
        } catch (ServiceException | InvalidFieldDAOException | InvalidParameterDAOException e) {
            //write log
            String mes = "Getting all transactions error";
            throw new ControllerException(mes,e);
        }

        return str.toString();
    }
}
