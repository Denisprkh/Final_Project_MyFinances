package by.prokhorenko.service.impl;

import by.prokhorenko.bean.transaction.Transaction;
import by.prokhorenko.bean.transaction.TransactionType;
import by.prokhorenko.bean.user.User;
import by.prokhorenko.dao.IUserDAO;
import by.prokhorenko.dao.exception.DAOException;
import by.prokhorenko.dao.factory.DAOFactory;
import by.prokhorenko.service.IUserService;
import by.prokhorenko.service.exception.ServiceException;
import by.prokhorenko.validation.Validation;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class UserServiceImpl implements IUserService {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private IUserDAO userDAO = daoFactory.getUserDAO();
    private TransactionServiceImpl transactionService = new TransactionServiceImpl();

    @Override
    public boolean registration(String login, String password) throws ServiceException {
        if(Validation.isNull(login) || login.isEmpty()){
            String mes = "Login_has_null_or_empty_value";
            throw new ServiceException(mes);
        }
        if(Validation.isNull(password) || password.isEmpty()){
            String mes = "Password_has_null_or_empty_value";
            throw new ServiceException(mes);
        }

        ArrayList<User> allUsers ;

        try {
            allUsers = userDAO.getAll();

        }catch (DAOException e){
            String mes = "Error_with_allUsers_ArrayList";
            throw new ServiceException(mes,e);
        }


        for(User user: allUsers){
            if(user.getLogin().equalsIgnoreCase(login)){
               return false;
            }
        }
        long id = allUsers.get(allUsers.size() - 1).getId() + 1;
        User user = new User(login,password,id);
            try {
                userDAO.add(user);
            }catch (DAOException e){
                String mes = "Error_with_adding_user";
                throw new ServiceException(mes,e);
            }

        return true;
    }

    @Override
    public boolean signIn(String login, String password) throws ServiceException {
        if(Validation.isNull(login) || login.isEmpty()){
            String mes = "Login_has_null_or_empty_value";
            throw new ServiceException(mes);
        }
        if(Validation.isNull(password) || password.isEmpty()){
            String mes = "Password_has_null_or_empty_value";
            throw new ServiceException(mes);
        }

        ArrayList<User> allUsers ;

        try {
            allUsers = userDAO.getAll();

        }catch (DAOException e){
            String mes = "Error_with_allUsers_ArrayList";
            throw new ServiceException(mes,e);
        }

        boolean result = false;

        for(User user : allUsers){
            if(user.getLogin().equalsIgnoreCase(login) && user.getPassword().equals(password)){
                result = true;
                break;
            }
        }

        return result;
    }

    @Override
    public void logOut() throws ServiceException {
        //used and implemented in view and for view
    }

    @Override
    public User get(String login) throws ServiceException {
        if(Validation.isNull(login)){
            String mes = "Login has null value";
            throw new ServiceException(mes);
        }
        User user;
        try {
             user = userDAO.get(login);
        } catch (DAOException e) {
            String mes = "Getting user by login error";
            throw new ServiceException(mes,e);
        }
        return user;
    }

    public BigDecimal getSumOfAllUsersTransactionsOfCertainType(User user, TransactionType transactionType)
            throws ServiceException {
        if(Validation.isNull(user)){
            String mes = "User has null value";
            throw new ServiceException(mes);
        }
        if(Validation.isNull(transactionType)){
            String mes = "TransactionType has null value";
            throw new ServiceException(mes);
        }

        ArrayList<Transaction> allUsersTransactionsOfCertainType =
                transactionService.getAllUsersTransactionsOfCertainType(user,transactionType);
        BigDecimal result = new BigDecimal(BigInteger.ZERO);
        for(Transaction transaction : allUsersTransactionsOfCertainType){
            result = result.add(transaction.getAmount());
        }

        return result;
    }
    @Override
    public BigDecimal getSumOfAllUsersTransactionsOfCertainTypeInAPeriod
            (User user, Date startPeriod, Date endPeriod,TransactionType transactionType) throws ServiceException {
        if(Validation.isNull(user)){
            String mes = "User has null value";
            throw new ServiceException(mes);
        }
        if(Validation.isNull(transactionType)){
            String mes = "Transaction type has null value";
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

        ArrayList<Transaction> allUsersTransactionsOfCertainTypeInAPeriod =
                transactionService.getAllUsersTransactionsOfCertainTypeInAPeriod(user,startPeriod,transactionType,endPeriod);
        BigDecimal result = new BigDecimal(BigInteger.ZERO);
        for(Transaction transaction : allUsersTransactionsOfCertainTypeInAPeriod){
            result = result.add(transaction.getAmount());
        }

        return result;
    }

}
