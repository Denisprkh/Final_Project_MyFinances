package by.prokhorenko.dao;

import by.prokhorenko.bean.transaction.Transaction;
import by.prokhorenko.bean.user.User;
import by.prokhorenko.dao.exception.DAOException;

import java.util.ArrayList;
import java.util.List;

public interface IUserDAO {
    void add(User user) throws DAOException;
    User get(int id) throws DAOException;
    User get(String login) throws DAOException;
    ArrayList<User> getAll() throws DAOException;
    void update (int id, User updatedUser) throws DAOException;
    void delete(int id) throws DAOException;
}
