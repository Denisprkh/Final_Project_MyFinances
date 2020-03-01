package by.prokhorenko.dao.factory;

import by.prokhorenko.bean.transaction.Transaction;
import by.prokhorenko.dao.TransactionDAO;
import by.prokhorenko.dao.UserDAO;
import by.prokhorenko.dao.impl.FileTransactionDAO;
import by.prokhorenko.dao.impl.FileUserDAO;

public class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new FileUserDAO();
    private final TransactionDAO  transactionDAO = new FileTransactionDAO();

    private DAOFactory() {};

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public TransactionDAO getTransactionDAO() {
        return transactionDAO;
    }
}
