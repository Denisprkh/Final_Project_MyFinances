package by.prokhorenko.controller.command.impl;

import by.prokhorenko.bean.transaction.Transaction;
import by.prokhorenko.bean.transaction.TransactionType;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GetAllTransactionsOfCertainTypeInAPeriod implements Command {
    @Override
    public String execute(String request) throws ControllerException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IUserService userService = serviceFactory.getUserService();
        ITransactionService transactionService = serviceFactory.getTransactionService();

        String login = Parser.getValueParam(request,"login");
        StringBuilder str = new StringBuilder();

        try {
            Date startPeriod = simpleDateFormat.parse(Parser.getValueParam(request, "startPeriod"));
            Date endPeriod = simpleDateFormat.parse(Parser.getValueParam(request, "endPeriod"));
            User user = userService.get(login);
            TransactionType transactionType =
                    TransactionType.valueOf(Parser.getValueParam(request,"transactionType"));
            ArrayList<Transaction> transactions =
                    transactionService.getAllUsersTransactionsOfCertainTypeInAPeriod(user,startPeriod,transactionType,endPeriod);
            if(transactions.size() == 0){
                str.append("You haven't got transactions of such tye in this period");
            }else{
                for(Transaction transaction : transactions){
                    str.append(TransactionConverter.convertTransactionToPrintableString(transaction)+"\n");
                }
            }
        } catch (ParseException | ServiceException | InvalidParameterDAOException | InvalidFieldDAOException e) {
            String mes = "Getting transactions of type in a period error";
            //write log
            throw new ControllerException(mes,e);
        }



        return str.toString();


    }
}
