package by.prokhorenko.service;

import by.prokhorenko.bean.user.User;
import by.prokhorenko.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Date;


public interface UserService {
    boolean registration(String login, String password) throws ServiceException;
    boolean signIn(String login, String password) throws ServiceException;
    BigDecimal getSumOfAllUsersExpenses(User user) throws ServiceException;
    BigDecimal getSumOfAllUsersIncomes(User user) throws ServiceException;
    BigDecimal getSumOfAllUsersExpensesInAPeriod(User user, Date startPeriod, Date endPeriod) throws ServiceException;
    BigDecimal getSumOfAllUsersIncomesInAPeriod(User user, Date startPeriod, Date endPeriod) throws ServiceException;
}
