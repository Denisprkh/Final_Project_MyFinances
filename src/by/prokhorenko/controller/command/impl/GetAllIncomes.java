package by.prokhorenko.controller.command.impl;

import by.prokhorenko.bean.transaction.Transaction;
import by.prokhorenko.controller.command.Command;
import by.prokhorenko.controller.exception.ControllerException;
import by.prokhorenko.dao.exception.InvalidFieldException;
import by.prokhorenko.dao.exception.InvalidParameterException;
import by.prokhorenko.service.ITransactionService;
import by.prokhorenko.service.IUserService;
import by.prokhorenko.service.exception.ServiceException;
import by.prokhorenko.service.factory.ServiceFactory;
import by.prokhorenko.util.Parser;
import by.prokhorenko.util.convertor.TransactionConverter;

import java.util.ArrayList;

public class GetAllIncomes implements Command {
    @Override
    public String execute(String request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IUserService userService = serviceFactory.getUserService();
        ITransactionService transactionService = serviceFactory.getTransactionService();

        String login = Parser.getValueParam(request,"login");
        ArrayList<Transaction> allIncomes;
        StringBuilder str = new StringBuilder();
        String response = " ";
        try{
            allIncomes = transactionService.getAllUsersIncomes(userService.get(login));
            for(Transaction transaction : allIncomes){

                str.append(TransactionConverter.convertTransactionToPrintableString(transaction)+"\n");
            }

            response = allIncomes.size() == 0 ? "You haven't got any incomes" : str.toString();
        } catch (ServiceException | InvalidParameterException | InvalidFieldException e) {
            //write log
            response = "Something wrong";
        }


        return response;


    }
}
