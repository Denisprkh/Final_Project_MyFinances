package by.prokhorenko.dao.factory;

import by.prokhorenko.dao.ITransactionDAO;
import by.prokhorenko.dao.IUserDAO;
import by.prokhorenko.dao.impl.FileTransactionDAO;
import by.prokhorenko.dao.impl.FileUserDAO;

public class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();

    private final IUserDAO userDAO = new FileUserDAO();
    private final ITransactionDAO transactionDAO = new FileTransactionDAO();

    private DAOFactory() {};

    public static DAOFactory getInstance() {
        return instance;
    }

    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public ITransactionDAO getTransactionDAO() {
        return transactionDAO;
    }
}
