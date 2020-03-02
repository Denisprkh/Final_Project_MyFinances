package by.prokhorenko.service.factory;

import by.prokhorenko.service.UserService;
import by.prokhorenko.service.impl.TransactionServiceImpl;
import by.prokhorenko.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final TransactionServiceImpl transactionService = new TransactionServiceImpl();
    private final UserServiceImpl userService = new UserServiceImpl();

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
