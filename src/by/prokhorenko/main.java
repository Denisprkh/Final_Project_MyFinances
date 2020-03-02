package by.prokhorenko;

import by.prokhorenko.bean.transaction.Transaction;
import by.prokhorenko.bean.transaction.TransactionType;
import by.prokhorenko.bean.user.User;
import by.prokhorenko.dao.TransactionDAO;
import by.prokhorenko.dao.UserDAO;
import by.prokhorenko.dao.exception.DAOException;
import by.prokhorenko.dao.exception.InvalidFieldException;
import by.prokhorenko.dao.exception.InvalidParameterException;
import by.prokhorenko.dao.factory.DAOFactory;
import by.prokhorenko.dao.impl.FileTransactionDAO;
import by.prokhorenko.dao.impl.FileUserDAO;
import by.prokhorenko.dao.util.TransactionConverter;
import by.prokhorenko.service.UserService;
import by.prokhorenko.service.exception.ServiceException;
import by.prokhorenko.service.impl.TransactionServiceImpl;
import by.prokhorenko.service.impl.UserServiceImpl;
import by.prokhorenko.util.FileUtilDAO;
import by.prokhorenko.util.exception.UtilException;
import static by.prokhorenko.util.ConstantsDAO.LINK_TRANSACTIONS;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class main {
    public static void main(String[] args) throws UtilException, InvalidFieldException, InvalidParameterException, ParseException, DAOException, ServiceException {
        /*Transaction transaction = new Transaction(TransactionType.EXPENSE,new BigDecimal(1000),1,12,new Date(),"Food");
        Transaction transaction1 = new Transaction(TransactionType.EXPENSE,new BigDecimal(1002),2,13,new Date(),"Food");
        Transaction transaction2 = new Transaction(TransactionType.EXPENSE,new BigDecimal(1004),2,13,new Date(),"Food");
        Transaction transaction3 = new Transaction(TransactionType.EXPENSE,new BigDecimal(1050),1,13,new Date(),"Food");
        FileTransactionDAO fileTransactionDAO = new FileTransactionDAO();
        fileTransactionDAO.add(transaction);
        fileTransactionDAO.add(transaction);
        fileTransactionDAO.add(transaction);
        fileTransactionDAO.add(transaction);
        fileTransactionDAO.add(transaction);
        fileTransactionDAO.add(transaction1);

        fileTransactionDAO.delete(1);

       */



        UserServiceImpl userService = new UserServiceImpl();
        TransactionServiceImpl transactionService = new TransactionServiceImpl();
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        TransactionDAO transactionDAO = daoFactory.getTransactionDAO();
        userService.registration("Denis", "12312");
        userService.registration("Pasha", "12312");
        userService.registration("Fedya", "12312");
        userService.registration("Kolya", "12312");
        userService.registration("Sasha", "12312");

        transactionService.add(new Transaction(TransactionType.EXPENSE, new BigDecimal(1231), new Date(1323132132L),2 ,"Food" ));
        transactionService.add(new Transaction(TransactionType.INCOME, new BigDecimal(5433), new Date(2112312L),2 ,"Food" ));
        transactionService.add(new Transaction(TransactionType.INCOME, new BigDecimal(1987), new Date(123214214L),3 ,"Food" ));
        transactionService.add(new Transaction(TransactionType.EXPENSE, new BigDecimal(166), new Date(13211231L),3 ,"Food" ));
        transactionService.add(new Transaction(TransactionType.INCOME, new BigDecimal(122), new Date(1232121333333L),2 ,"Food" ));
        transactionService.add(new Transaction(TransactionType.EXPENSE, new BigDecimal(1123), new Date(455343L),4 ,"Food" ));
        transactionService.add(new Transaction(TransactionType.INCOME, new BigDecimal(122), new Date(6445444L),4 ,"Food" ));
        transactionService.add(new Transaction(TransactionType.EXPENSE, new BigDecimal(125), new Date(4435434L),2 ,"Food" ));


        ArrayList<User> allUsers = userDAO.getAll();
        User user1 = allUsers.get(0);
        User user2 = allUsers.get(1);
        User user3 = allUsers.get(2);
        User user4 = allUsers.get(3);
        User user5 = allUsers.get(4);
        BigDecimal d = userService.getSumOfAllUsersIncomesInAPeriod(user3, new Date(321L), new Date(2112312L));

        System.out.println(d);



    }
}
