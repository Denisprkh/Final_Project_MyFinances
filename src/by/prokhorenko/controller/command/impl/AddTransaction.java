package by.prokhorenko.controller.command.impl;

import by.prokhorenko.bean.transaction.Transaction;
import by.prokhorenko.bean.transaction.TransactionType;
import by.prokhorenko.bean.user.User;
import by.prokhorenko.controller.command.Command;
import by.prokhorenko.service.ITransactionService;
import by.prokhorenko.service.IUserService;
import by.prokhorenko.service.exception.ServiceException;
import by.prokhorenko.service.factory.ServiceFactory;
import by.prokhorenko.util.Parser;
import by.prokhorenko.validation.Validation;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTransaction implements Command {
    @Override
    public String execute(String request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ITransactionService transactionService = serviceFactory.getTransactionService();
        IUserService userService = serviceFactory.getUserService();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yy");

        String login = Parser.getValueParam(request, "login");
        String type = Parser.getValueParam(request, "type");
        String amount = Parser.getValueParam(request,"amount");
        String comment = Parser.getValueParam(request, "comment");
        String date = Parser.getValueParam(request,"date");
        String response;
        TransactionType transactionType;
        transactionType = type.equalsIgnoreCase("income") ? TransactionType.INCOME : (
                type.equalsIgnoreCase("expense") ? TransactionType.EXPENSE : null);
        if(Validation.isNull(transactionType)){
            return "Invalid transaction type";
        }
        try {
            Date date1 = dateFormat.parse(date);
            User user = userService.get(login);
            long usersId = user.getId();
            Transaction transaction = new Transaction(transactionType,new BigDecimal(amount),date1,usersId,comment);
            transactionService.add(transaction);
            response = "Transaction was added to your transaction list";
        } catch (ServiceException | ParseException e) {
            response = "Problems with adding, please retry";
            //write log
        }



            return response;
    }
}
