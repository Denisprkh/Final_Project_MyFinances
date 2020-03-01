package by.prokhorenko.dao;

import by.prokhorenko.bean.transaction.Transaction;
import by.prokhorenko.dao.exception.DAOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface TransactionDAO {
    void add(Transaction transaction) throws DAOException;
    Transaction get(long id) throws DAOException;
    ArrayList<Transaction> getAll() throws DAOException;
    void update(long id, Transaction transaction) throws DAOException;
    void delete(long id) throws DAOException;



}
