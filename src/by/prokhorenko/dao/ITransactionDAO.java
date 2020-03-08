package by.prokhorenko.dao;

import by.prokhorenko.bean.transaction.Transaction;
import by.prokhorenko.dao.exception.DAOException;
import java.util.ArrayList;


public interface ITransactionDAO {
    void add(Transaction transaction) throws DAOException;
    Transaction get(long id) throws DAOException;
    ArrayList<Transaction> getAll() throws DAOException;
    void update(long id, Transaction transaction) throws DAOException;
    void delete(long id) throws DAOException;



}
