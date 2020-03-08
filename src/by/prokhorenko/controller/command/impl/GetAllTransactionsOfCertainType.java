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

import java.util.ArrayList;

public class GetAllTransactionsOfCertainType implements Command {
    @Override
    public String execute(String request) throws ControllerException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        IUserService userService = serviceFactory.getUserService();
        ITransactionService transactionService = serviceFactory.getTransactionService();

        String login = Parser.getValueParam(request,"login");
        String type = Parser.getValueParam(request,"transactionType");
        StringBuilder st = new StringBuilder();
        try {
            User user = userService.get(login);
            ArrayList<Transaction> transactions =
                    transactionService.getAllUsersTransactionsOfCertainType(user, TransactionType.valueOf(type));
            if(transactions.size() == 0){
                st.append("You haven't got any transactions of this type");
            }else {
                for (Transaction transaction : transactions) {
                    st.append(TransactionConverter.convertTransactionToPrintableString(transaction) + "\n");

                }
            }
        } catch (ServiceException | InvalidParameterDAOException | InvalidFieldDAOException e) {
            //write log
            String mes = "Getting users transactions of certain type error";
            throw new ControllerException(mes,e);
        }

        return st.toString();
    }
}
