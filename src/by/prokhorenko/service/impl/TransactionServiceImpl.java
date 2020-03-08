package by.prokhorenko.service.impl;

import by.prokhorenko.bean.transaction.Transaction;
import by.prokhorenko.bean.transaction.TransactionType;
import by.prokhorenko.bean.user.User;
import by.prokhorenko.dao.ITransactionDAO;
import by.prokhorenko.dao.exception.DAOException;
import by.prokhorenko.dao.factory.DAOFactory;
import by.prokhorenko.service.ITransactionService;
import by.prokhorenko.service.exception.ServiceException;
import by.prokhorenko.validation.Validation;

import java.util.ArrayList;
import java.util.Date;

public class TransactionServiceImpl implements ITransactionService {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private ITransactionDAO transactionDAO = daoFactory.getTransactionDAO();


    @Override
    public void add(Transaction transaction) throws ServiceException {
        if(Validation.isNull(transaction)){
            String mes = "Transaction is null";
            throw new ServiceException(mes);
        }
        try {

            transactionDAO.add(transaction);
        }catch (DAOException e){
            String mes = "Transaction adding error";
            throw new ServiceException(mes,e);
        }

    }

    @Override
    public ArrayList<Transaction> getAllUsersTransactions(User user) throws ServiceException {
        if(Validation.isNull(user)){
            String mes = "User has null value";
            throw new ServiceException(mes);
        }

        ArrayList<Transaction> allTransactions ;
        try {
            allTransactions = transactionDAO.getAll();
        }catch (DAOException e){
            String mes = "Getting all transactions error";
            throw new ServiceException(mes,e);
        }

        ArrayList<Transaction> usersTransactions = new ArrayList<>();

        for(Transaction transaction : allTransactions){
            if(user.getId() == transaction.getUsersId()){
                usersTransactions.add(transaction);
            }
        }

        return usersTransactions;
    }

    @Override
    public ArrayList<Transaction> getAllUsersTransactionsInAPeriod(User user, Date startPeriod, Date endPeriod)
            throws ServiceException {
        if(Validation.isNull(user)){
            String mes = "User has null value";
            throw new ServiceException(mes);
        }
        if(Validation.isNull(startPeriod) || Validation.isNull(endPeriod)){
            String mes = "Date has null value";
            throw new ServiceException(mes);
        }
        if(!Validation.datePeriodIsCorrect(startPeriod,endPeriod)){
            String mes = "Incorrect date period";
            throw new ServiceException(mes);
        }

        ArrayList<Transaction> allUsersTransactions = getAllUsersTransactions(user);
        ArrayList<Transaction> usersTransactionsInAPeriod = new ArrayList<>();
        for(Transaction transaction : allUsersTransactions){
            if(transaction.getDate().after(startPeriod) && transaction.getDate().before(endPeriod)){
                usersTransactionsInAPeriod.add(transaction);
            }
        }

        return usersTransactionsInAPeriod;
    }

    @Override
    public ArrayList<Transaction> getAllUsersTransactionsOfCertainType(User user, TransactionType transactionType) throws ServiceException {
        if(Validation.isNull(user)){
            String mes = "User has null value";
            throw new ServiceException(mes);
        }
        if(Validation.isNull(transactionType)){
            String mes = "Transaction type is null";
            throw new ServiceException(mes);
        }

        ArrayList<Transaction> allUsersTransactions = getAllUsersTransactions(user);

        ArrayList<Transaction> usersTransactionsOfType = new ArrayList<>();

        for(Transaction transaction : allUsersTransactions){
            if(transaction.getTransactionType().equals(transactionType)){
                usersTransactionsOfType.add(transaction);
            }
        }

        return usersTransactionsOfType;
    }
    @Override
    public ArrayList<Transaction> getAllUsersTransactionsOfCertainTypeInAPeriod
            (User user, Date startPeriod, TransactionType transactionType,Date endPeriod) throws ServiceException {
        if(Validation.isNull(user)){
            String mes = "User has null value";
            throw new ServiceException(mes);
        }
        if(Validation.isNull(transactionType)){
            String mes = "User has null value";
            throw new ServiceException(mes);
        }
        if(Validation.isNull(startPeriod) || Validation.isNull(endPeriod)){
            String mes = "Date has null value";
            throw new ServiceException(mes);
        }
        if(!Validation.datePeriodIsCorrect(startPeriod,endPeriod)){
            String mes = "Incorrect date period";
            throw new ServiceException(mes);
        }


        ArrayList<Transaction> usersTransactionsInAPeriod = getAllUsersTransactionsInAPeriod(user,startPeriod,endPeriod);
        ArrayList<Transaction> usersTransactionsOfCertainTypeInAPeriod  = new ArrayList<>();
        for(Transaction transaction : usersTransactionsInAPeriod){
            if(transaction.getTransactionType().equals(transactionType)){
                usersTransactionsOfCertainTypeInAPeriod.add(transaction);
            }
        }

        return usersTransactionsOfCertainTypeInAPeriod;
    }


}
