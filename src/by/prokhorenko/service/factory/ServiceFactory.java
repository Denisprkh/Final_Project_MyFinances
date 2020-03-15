package by.prokhorenko.service.factory;

import by.prokhorenko.service.impl.TransactionServiceImpl;
import by.prokhorenko.service.impl.UserServiceImpl;
import by.prokhorenko.dao.ITransactionDAO;
import by.prokhorenko.dao.IUserDAO;

public class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final ITransactionService transactionService = new TransactionServiceImpl();
    private final IUserService userService = new UserServiceImpl();

    private ServiceFactory(){

    }

    public static ServiceFactory getInstance(){
        return INSTANCE;
    }

    public TransactionServiceImpl getTransactionService(){
        return transactionService;
    }

    public UserServiceImpl getUserService(){
        return userService;
    }


}
