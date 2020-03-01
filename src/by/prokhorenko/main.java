package by.prokhorenko;

import by.prokhorenko.bean.transaction.Transaction;
import by.prokhorenko.bean.transaction.TransactionType;
import by.prokhorenko.bean.user.User;
import by.prokhorenko.dao.exception.DAOException;
import by.prokhorenko.dao.exception.InvalidFieldException;
import by.prokhorenko.dao.exception.InvalidParameterException;
import by.prokhorenko.dao.impl.FileTransactionDAO;
import by.prokhorenko.dao.impl.FileUserDAO;
import by.prokhorenko.dao.util.TransactionConverter;
import by.prokhorenko.util.FileUtilDAO;
import by.prokhorenko.util.exception.UtilException;
import static by.prokhorenko.util.ConstantsDAO.LINK_TRANSACTIONS;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class main {
    public static void main(String[] args) throws UtilException, InvalidFieldException, InvalidParameterException, ParseException, DAOException {
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

        User user = new User("Denis","212312KOs",12,new BigDecimal(12123));
        User user2 = new User("Petr","212312KOs",123,new BigDecimal(12123));
        User user3 = new User("Gulya","212312KOs",122,new BigDecimal(12123));
        User user5 = new User("UPDATED","212312KOs",1211,new BigDecimal(12123));

        FileUserDAO fileUserDAO = new FileUserDAO();
       fileUserDAO.delete(1211);

    }
}
