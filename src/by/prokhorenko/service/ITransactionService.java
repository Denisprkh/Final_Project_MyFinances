package by.prokhorenko.service;

import by.prokhorenko.bean.transaction.Transaction;
import by.prokhorenko.bean.user.User;
import by.prokhorenko.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public interface ITransactionService {
    void add(Transaction transaction) throws ServiceException;
    ArrayList<Transaction> getAllUsersTransactions (User user) throws ServiceException;
    ArrayList<Transaction> getAllUsersExpenses(User user) throws ServiceException;
    ArrayList<Transaction> getAllUsersIncomes(User user) throws ServiceException;
    ArrayList<Transaction> getAllUsersTransactionsInAPeriod(User user, Date startPeriod, Date endPeriod)
            throws ServiceException;
    ArrayList<Transaction> getAllUsersIncomesInAPeriod(User user, Date startPeriod, Date endPeriod)
            throws ServiceException;
    ArrayList<Transaction> getAllUsersExpensesInAPeriod(User user, Date startPeriod, Date endPeriod)
            throws ServiceException;

}
