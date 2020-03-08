package by.prokhorenko.service;

import by.prokhorenko.bean.transaction.TransactionType;
import by.prokhorenko.bean.user.User;
import by.prokhorenko.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Date;


public interface IUserService {
    boolean registration(String login, String password) throws ServiceException;
    boolean signIn(String login, String password) throws ServiceException;
    void logOut() throws ServiceException;
    User get(String login) throws ServiceException;
    BigDecimal getSumOfAllUsersTransactionsOfCertainTypeInAPeriod
            (User user, Date startPeriod, Date endPeriod, TransactionType transactionType) throws ServiceException;
    BigDecimal getSumOfAllUsersTransactionsOfCertainType(User user, TransactionType transactionType)
            throws ServiceException;

}
